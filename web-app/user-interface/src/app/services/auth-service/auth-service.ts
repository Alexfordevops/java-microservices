import { Injectable } from '@angular/core';
import {UserRegister} from '../../interfaces/UserRegister';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //Endpoint da api auth-service/register
  private apiUrl = 'url:port/users/register';

  //Constroi as classes
  constructor(
    private http: HttpClient
  ) {}

  //Metodo de registro
  register(user: UserRegister): Observable<any>{
    return this.http.post(this.apiUrl, user); //criar concatenação para o endpoint de registro
  }

  //Metodo de login: criar forma de receber e armazenar token jwt
  login(user: UserRegister): Observable<any>{
    return this.http.post(this.apiUrl, user); //criar concatenação para o endpoint de registro
  }
}
