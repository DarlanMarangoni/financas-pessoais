import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = false;

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    // Simulação de autenticação enquanto não temos um backend
    // Substitua por uma chamada HTTP real quando tiver o backend pronto
    if (email === 'user@example.com' && password === '123456') {
      this.isAuthenticated = true;
      // Simular o delay de uma chamada de rede
      return of({ success: true, user: { email } }).pipe(delay(1000));
    }
    return throwError(() => 'Email ou senha inválidos').pipe(delay(1000));
  }

  logout(): Observable<any> {
    this.isAuthenticated = false;
    return of({ success: true }).pipe(delay(500));
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }
}
