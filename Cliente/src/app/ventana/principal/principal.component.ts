import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Personaje } from 'src/app/menu/Personaje';
import { PersonajeServicesService } from 'src/app/services/personaje-services.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent implements OnInit{
  personajes: Personaje[] = [];
  personaje: Personaje = new Personaje()
  personajeForm: FormGroup
  codigos?: number;

  constructor(private personajeService: PersonajeServicesService, private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) {

      this.personajeForm = this.fb.group({
        marca: ['', Validators.required],
        color: ['', Validators.required],
        placa: ['', Validators.required]
      });
    }

  ngOnInit(): void {
    this.loadPersonajes();
    const codigoParam = this.route.snapshot.paramMap.get('codigo');
    if (codigoParam !== null) {
      this.codigos = +codigoParam;
      if (this.codigos) {
        this.personajeService.getPersonaje(this.codigos).subscribe((data: Personaje) => {
          this.personajeForm.patchValue(data);
        });
      }
    } else {
      this.codigos = 0; // O cualquier valor por defecto que consideres apropiado
    }
  }

  loadPersonajes(): void {
    this.personajeService.getPersonajes().subscribe((data: Personaje[]) => {
      this.personajes = data;
    });
  }

  deletePersonaje(codigo: number | undefined): void {
    this.personajeService.deletePersonaje(codigo).subscribe(data => {
      this.loadPersonajes();
      console.log(data)
    });
  }

  add(): void {
        this.personajeService.addPersonaje(this.personaje).subscribe(data => {
          console.log(data)
          location.reload()
        });
      }

  update(): void{
    this.personajeService.updatePersonaje(this.personaje).subscribe(data=> {
    console.log(data)
    location.reload()
  })}
    
  
  selected(numero : any){
    this.personajeService.getPersonaje(numero).subscribe(data=>{
      console.log(data)
      this.personaje = data
    })
  }
}
