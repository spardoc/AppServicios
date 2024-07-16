import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Personaje } from 'src/app/menu/Personaje';
import { PersonajeServicesService } from 'src/app/services/personaje-services.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent implements OnInit {
  personajes: Personaje[] = [];
  personaje: Personaje = new Personaje();
  personajeForm: FormGroup;
  codigos?: number;

  constructor(
    private personajeService: PersonajeServicesService,
    private fb: FormBuilder,
    private route: ActivatedRoute
  ) {
    this.personajeForm = this.fb.group({
      nombre: ['', Validators.required],
      edad: ['', Validators.required],
      altura: ['', Validators.required],
      peso: ['', Validators.required],
      genero: ['', Validators.required],
      rol: ['', Validators.required],
      correo: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadPersonajes();
    const codigoParam = this.route.snapshot.paramMap.get('codigo');
    if (codigoParam) {
      this.codigos = +codigoParam;
      this.personajeService.getPersonaje(this.codigos).subscribe((data: Personaje) => {
        this.personajeForm.patchValue(data);
      });
    }
  }

  loadPersonajes(): void {
    this.personajeService.getPersonajes().subscribe((data: Personaje[]) => {
      this.personajes = data;
    });
  }

  deletePersonaje(codigo: number | undefined): void {
    if (typeof codigo === 'number') {
      this.personajeService.deletePersonaje(codigo).subscribe(() => {
        this.loadPersonajes();
      });
    } else {
      console.warn("Intento de eliminar un personaje sin código válido.");
    }
  }  

  add(): void {
    const newPersonaje = this.personajeForm.value;
    this.personajeService.addPersonaje(newPersonaje).subscribe(() => {
      location.reload();
    });
  }

  update(): void {
    const updatedPersonaje = {...this.personaje, ...this.personajeForm.value};
    this.personajeService.updatePersonaje(updatedPersonaje).subscribe({
      next: (response) => {
        console.log('Update successful', response);
        location.reload();  // Recarga la página para mostrar los cambios actualizados
      },
      error: (error) => {
        console.error('Update failed', error);
      }
    });
  }
  

  selected(codigo: number | undefined): void {
    if (typeof codigo === 'number') {
      this.personajeService.getPersonaje(codigo).subscribe(data => {
        this.personaje = data;  // Asegúrate de que personaje está actualizado
        this.personajeForm.patchValue(data);  // Actualiza el formulario con los datos del personaje
      });
    } else {
      console.error("El código del personaje es undefined, selección fallida");
    }
  }  
}
