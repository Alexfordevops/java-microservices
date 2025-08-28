import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service/auth-service';

export const authGuard: CanActivateFn = (route, state) => {

  //Chama os serviços
  const authService = inject(AuthService);
  const router = inject(Router);

  //Recebe o token
  const token = authService.getToken();

  //Faz a validação do token
  if (token) {
    return true; // permite acessar a rota
  } else {
    router.navigate(['/login']); // redireciona para login
    return false;
  }
};
