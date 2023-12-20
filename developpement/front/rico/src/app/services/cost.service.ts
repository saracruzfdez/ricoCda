import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export enum AverageCostEnum {
  ECONOMIQUE = "Économique",
  MODERE = "Modéré",
  COUTEUX = "Coûteux"
}

@Injectable({
  providedIn: 'root'
})

export class CostService {
  private apiUrl = 'http://localhost:9000/costs';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getCosts(): Observable<AverageCostEnum[]> {
    return this.http.get<AverageCostEnum[]>(this.apiUrl, { headers: this.getHeaders() });
  }
}