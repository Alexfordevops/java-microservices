import { Routes } from '@angular/router';
import {RegisterPage} from './features/user-auth/register-page/register-page';

export const routes: Routes = [

  {path: "", redirectTo: "/register", pathMatch: "full"},
  {path: "register", component: RegisterPage},
];
