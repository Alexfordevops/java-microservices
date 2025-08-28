import { Component } from '@angular/core';
import {CreateProductForm} from '../create-product-form/create-product-form';

@Component({
  selector: 'app-product-page',
  standalone: true,
  imports: [
    CreateProductForm
  ],
  templateUrl: './product-page.html',
  styleUrl: './product-page.css'
})
export class ProductPage {

}
