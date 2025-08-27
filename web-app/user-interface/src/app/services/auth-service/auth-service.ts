import { Injectable } from '@angular/core';
import {UserRegister} from '../../interfaces/UserRegister';
import {UserLogin} from '../../interfaces/UserLogin';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //Endpoint da api auth-service/register
  private apiUrl = 'http://192.168.56.10:8080/auth';

  //Constroi as classes
  constructor(
    private http: HttpClient
  ) {}

  // Metodo de registro
  register(userForm: UserRegister): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userForm)
      .pipe(
        catchError(error => {
          const errorMsg = error.error?.error || 'Erro ao registrar usuário';
          return throwError(() => new Error(errorMsg));
        })
      );
  }

  // Metodo de login: recebe e armazena token JWT
  login(userForm: UserLogin): Observable<any> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, userForm)
      .pipe(
        tap(response => {
          if (response.token) {
            localStorage.setItem('authToken', response.token); // salva token
          }
        }),
        catchError(error => {
          const errorMsg = error.error?.error || 'Erro ao efetuar o login';
          return throwError(() => new Error(errorMsg));
        })
      );
  }

  // Recupera o token salvo
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Remove token (logout)
  logout(): void {
    localStorage.removeItem('authToken');
  }
}
