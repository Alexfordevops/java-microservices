import { Component } from '@angular/core';
import {CreateProductForm} from '../create-product-form/create-product-form';
import {ProductTable} from '../product-table/product-table';

@Component({
  selector: 'app-product-page',
  standalone: true,
  imports: [
    CreateProductForm,
    ProductTable
  ],
  templateUrl: './product-page.html',
  styleUrl: './product-page.css'
})
export class ProductPage {

}
