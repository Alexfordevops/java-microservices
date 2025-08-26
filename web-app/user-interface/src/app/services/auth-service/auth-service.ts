import { Injectable } from '@angular/core';
import {UserRegister} from '../../interfaces/UserRegister';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

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

  //Metodo de registro
  register(userForm: UserRegister): Observable<any>{
    return this.http.post(`${this.apiUrl}/register`, userForm ); //criar concatenação para o endpoint de registro
  }

  //Metodo de login: criar forma de receber e armazenar token jwt
  login(userForm: UserRegister): Observable<any>{
    return this.http.post(`${this.apiUrl}/login`, userForm); //criar concatenação para o endpoint de registro
  }
}
