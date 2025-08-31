import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service/auth-service';

export const authGuard: CanActivateFn = (route, state) => {

  //Chama os serviços
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn()) {
    return true; // pode acessar
  } else {
    router.navigate(['/login']); // redireciona para login
    return false;
  }
};
