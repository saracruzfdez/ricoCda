import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export enum CategoryEnum {
  ENTREE = 'Entrée',
  PLAT = 'Plat',
  DESSERT = 'Dessert'
}

@Injectable({
  providedIn: 'root',
})

export class CategoryService {
  private apiUrl = 'http://localhost:9000/categories';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getCategories(): Observable<CategoryEnum[]> {
    return this.http.get<CategoryEnum[]>(this.apiUrl, { headers: this.getHeaders() });
  }
}