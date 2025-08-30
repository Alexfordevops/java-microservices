import { Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import {ProductService} from '../../../services/product-service/product-service';
import {ProductList} from '../../../interfaces/ProductList';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-table',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './product-table.html',
  styleUrl: './product-table.css'
})
export class ProductTable{

  products:ProductList[] = [];
  loading:boolean = false;
  filters = {
    name: '',
    category: '',
    minPrice: null as number | null,
    maxPrice: null as number | null,
    minQuantity: null as number | null,
    maxQuantity: null as number | null
  }

  constructor(
    private productService: ProductService
  ) {}

  listProducts(){

    this.products = []; // limpa a tabela enquanto busca

    //Lista a os produtos com filtro
    this.productService.listUserProductsFiltered(this.filters).subscribe({
      next: (data) => {
        this.products = data;
        console.log("Produtos carregados com sucesso", data);
      },
      error: (err) => {
        console.log("Erro ao carregar produtos", err);
      }
    })
  }
}
