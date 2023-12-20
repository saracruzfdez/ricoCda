import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export enum DifficultyEnum {
  DEBUTANT = "Débutant",
  INTERMEDIAIRE = "Intermédiaire",
  AVANCE = "Avancé"
}

@Injectable({
  providedIn: 'root'
})

export class DifficultyService {
  private apiUrl = 'http://localhost:9000/difficulties';

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
  
  getDifficulties(): Observable<DifficultyEnum[]> {
    return this.http.get<DifficultyEnum[]>(this.apiUrl, { headers: this.getHeaders() });
  }
}