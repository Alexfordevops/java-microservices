import { Routes } from '@angular/router';
import {RegisterPage} from './features/user-auth/register-page/register-page';
import {LoginPage} from './features/user-auth/login-page/login-page';

export const routes: Routes = [

  {path: "", redirectTo: "/register", pathMatch: "full"},
  {path: "register", component: RegisterPage},
  {path: "login", component: LoginPage}
];
