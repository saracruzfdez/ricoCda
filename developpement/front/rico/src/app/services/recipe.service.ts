import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

export interface Recipe {
  id?: number;
  image_path: string;
  category: string;
  title: string;
  prep_time: number;
  cook_time: number;
  persons_number: number;
  difficulty: string;
  average_cost: string;
  country_origin: string;
  ingredients: [];
  step: [];
}

@Injectable({
  providedIn: 'root'
})

export class RecipeService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Array<Recipe>> {
    return this.http.get('http://localhost:9000/recipes') as Observable<Array<Recipe>>;
  }
    // // Función para obtener los encabezados con el token JWT
    // private getHeaders(): HttpHeaders {
    //   const token = sessionStorage.getItem('token');
    //   return new HttpHeaders({
    //     'Authorization': `Bearer ${token}`
    //   });
    // }
  
    // // Función para manejar errores
    // private handleError(error: any): Observable<never> {
    //   console.error('Error en la solicitud:', error);
    //   return throwError(error);
    // }
}
