import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {ProductCreate} from '../../interfaces/ProductCreate';
import {ProductList} from '../../interfaces/ProductList';
import {catchError} from 'rxjs/operators';
import {AuthService} from '../auth-service/auth-service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //Endpoint da api product-service
  private apiUrl: string = 'http://192.168.56.10:8080/products';

  //Constroi as classes
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) {}

  //Metodo criar produto
  public createProduct(productForm: ProductCreate): Observable<any>{

    return this.http.post(`${this.apiUrl}/create`, productForm)
      .pipe(
        catchError(error => {
          const errorMsg = error.error?.error || 'Erro ao criar produto';
          return throwError(() => new Error(errorMsg));
        })
      );
  }

  //Metodo listar todos os produtos do usuario
  public listUserProducts(): Observable<ProductList[]>{
    return this.http.get<ProductList[]>(`${this.apiUrl}/list/me`);
  }

  //Metodo listar todos os produtos do usuario com filtros
  listUserProductsFiltered(filters: any): Observable<ProductList[]> {
    let params = new HttpParams();
    for (const key in filters) {
      if (filters[key] !== null && filters[key] !== '') {
        params = params.set(key, filters[key]);
      }
    }
    return this.http.get<ProductList[]>(`${this.apiUrl}/list/me/filter`, { params });
  }
}
