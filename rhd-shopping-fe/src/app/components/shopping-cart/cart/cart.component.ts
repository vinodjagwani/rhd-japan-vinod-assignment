import { ResourceLoader } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';

import { Product } from 'src/app/models/product';

import { MessengerService } from 'src/app/services/messenger.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems = [];

  cartTotal = 0

  constructor(private msg: MessengerService) { }

  ngOnInit() {
    this.msg.getMsg().subscribe((product: Product) => {
      this.addProductToCart(product)
    })
  }

  addProductToCart(product: Product) {

    let productExists = false

    for (let i in this.cartItems) {
      if (this.cartItems[i].productId === product.id) {
        this.cartItems[i].qty++
        productExists = true
        break;
      }
    }

    if (!productExists) {
      this.cartItems.push({
        productId: product.id,
        productName: product.name,
        qty: 1,
        price: product.price
      })
    }
    this.cartTotal = 0
    this.cartItems.forEach(item => {
      this.cartTotal += (item.qty * item.price)
    })
  }

  clearcart(){
     this.cartItems=[0];
     this.cartTotal=0;
    // window.location.reload();
  }

}
