import { HttpClient } from '@angular/common/http';
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
  getDifficulties(): Observable<DifficultyEnum[]> {
    return this.http.get<DifficultyEnum[]>(this.apiUrl);
  }
}