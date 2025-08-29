import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ProductService} from '../../../services/product-service/product-service';
import {ProductList} from '../../../interfaces/ProductList';

@Component({
  selector: 'app-product-table',
  standalone: true,
  imports: [
    CommonModule,
  ],
  templateUrl: './product-table.html',
  styleUrl: './product-table.css'
})
export class ProductTable implements OnInit{

  products:ProductList[] = [];
  loading:boolean = true;

  constructor(
    private productService: ProductService
  ) {}

  ngOnInit():void {

    this.productService.listUserProducts().subscribe({
      next: (data) => {
        this.products = data;
        this.loading = false;
        console.log("Produtos carregados com sucesso", data);
      },
      error: (err) => {
        console.log("Erro ao carregar produtos", err);
        this.loading = false;
      }
    })
  }
}
