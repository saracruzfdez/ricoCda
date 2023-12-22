import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

export interface Ingredient {
  id: number;
  name: string;
  quantity: number;
  unit: string;
}

export interface Step {
  id: number;
  name: string;
  description: string;
}

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
  ingredients: Ingredient[];
  steps: Step[];
}

@Injectable({
  providedIn: 'root'
})

export class RecipeService {

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getAll(): Observable<Array<Recipe>> {
    return this.http.get('http://localhost:9000/recipes', { headers: this.getHeaders() }) as Observable<Array<Recipe>>;
  }

  getById(id: number): Observable<Recipe> | null {
    if (id === null) {
      return null;
    }
    return this.http.get(`http://localhost:9000/recipes/${id}`, { headers: this.getHeaders() }) as Observable<Recipe>;
  }

  add(newRecipe: Recipe, id: any): Observable<Recipe> {
    return this.http.post(`http://localhost:9000/recipes/users/${id}/recipes`, newRecipe, { headers: this.getHeaders() }) as Observable<Recipe>;
  }

  update(id: number, updatedRecipe: Recipe): Observable<Recipe> {
    const url = `http://localhost:9000/recipes/${id}`;
    return this.http.put(url, updatedRecipe, { headers: this.getHeaders() }) as Observable<Recipe>;
  }
  
    private handleError(error: any): Observable<never> {
      console.error('Error request:', error);
      return throwError(error);
    }
    
}