import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Product } from 'src/app/models/product'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  products: Product[] = [
  ]

  constructor(private http: HttpClient) { }

  getProducts(): Product[] {
    //TODO: Populate products from an API
   this.http.get('http://localhost:8084/product-service/v1/items')
   .subscribe((res : any[])=>{
      for (const d of (res['content'] as any)) {
          this.products.push(new Product(d.itemId, d.name, d.description, d.itemPrice, 'http://localhost:8084/product-service/v1/items/media/' + d.imageId));
       }
    });
    return this.products
  }
}

