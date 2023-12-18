import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export enum UnitEnum {
  ML = "Millilitres",
  CL = "Centilitres",
  L = "Litres",
  CUILLERE_A_SOUPE = "Cuillère à soupe",
  G = "Grammes",
  MG = "Milligrammes",
  KG = "Kilogrammes"
}

@Injectable({
  providedIn: 'root'
})

export class UnitService {

  private apiUrl = 'http://localhost:9000/unities';

  constructor(private http: HttpClient) { }
  getUnities(): Observable<UnitEnum[]> {
    return this.http.get<UnitEnum[]>(this.apiUrl);
  }
}