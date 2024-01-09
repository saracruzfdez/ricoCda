import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

export interface DecodeToken {
  sub: string,
  iat: number,
  exp: number
}

@Injectable({
  providedIn: 'root'
})

export class JwtTokenService {

  jwtToken!: string;
  decodedToken!: DecodeToken

  constructor() {
  }

  setToken(token: string): void {
    if (token) {
      this.jwtToken = token;
    }
  }

  decodeToken() {
    if (this.jwtToken) {
      this.decodedToken = jwtDecode(this.jwtToken);
    }
  }

  getDecodeToken() {
    return jwtDecode(this.jwtToken);
  }





  // getUser(): string | null {
  //   this.decodeToken();
  //   return this.decodedToken ? this.decodedToken.sub : null;
  // }

  //Quitar ??
  getUser(): number | null {
    this.decodeToken();
    const userIdString = this.decodedToken?.sub;

    if (userIdString !== null && userIdString !== undefined) {
      const userId = parseInt(userIdString, 10);
      return !isNaN(userId) ? userId : null;
    }

    return null;
  }

  getUserId(): number | null {
    this.decodeToken();
    const userIdString = this.decodedToken?.sub;

    if (userIdString !== null && userIdString !== undefined) {
      const userId = parseInt(userIdString, 10);
      return !isNaN(userId) ? userId : null;
    }

    return null;
  }
//Quitar ?





  getExpiryTime() {
    this.decodeToken();
    return this.decodedToken ? this.decodedToken.exp : null;
  }

  isTokenExpired(): boolean {
    const expiryTime: number | null = this.getExpiryTime();
    if (expiryTime) {
      return ((1000 * expiryTime) - (new Date()).getTime()) < 5000;
    }
    return false;
  }
}