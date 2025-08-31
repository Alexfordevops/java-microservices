import { Routes } from '@angular/router';
import {RegisterPage} from './features/user-auth/register-page/register-page';
import {LoginPage} from './features/user-auth/login-page/login-page';
import {ProductPage} from './features/product-dashboard/product-page/product-page';
import {authGuard} from './guards/auth-guard';

export const routes: Routes = [

  { path: "register", component: RegisterPage },
  { path: "login", component: LoginPage },
  { path: "products", component: ProductPage, canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' } // <- sempre a última!
];
