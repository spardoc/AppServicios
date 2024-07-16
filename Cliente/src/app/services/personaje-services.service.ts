import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Personaje } from '../menu/Personaje';
@Injectable({
  providedIn: 'root'
})
export class PersonajeServicesService {

  private apiUrl = 'business/rs/personaje';

  constructor(private http: HttpClient) {}

  getPersonajes(): Observable<Personaje[]> {
    return this.http.get<Personaje[]>(this.apiUrl + '/list');
  }

  getPersonaje(codigo: number): Observable<Personaje> {
    return this.http.get<Personaje>(`${this.apiUrl}/${codigo}`);
  }

  addPersonaje(personaje: Personaje): Observable<Personaje> {
    return this.http.post<Personaje>(this.apiUrl, personaje);
  }

  updatePersonaje(personaje: Personaje): Observable<Personaje> {
    return this.http.put<Personaje>(`${this.apiUrl}`, personaje);
  }

  deletePersonaje(codigo: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}?id=${codigo}`);
  }
}