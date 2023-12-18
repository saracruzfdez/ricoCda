import { HttpClient } from '@angular/common/http';
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

  getCategories(): Observable<CategoryEnum[]> {
    return this.http.get<CategoryEnum[]>(this.apiUrl);
  }
}