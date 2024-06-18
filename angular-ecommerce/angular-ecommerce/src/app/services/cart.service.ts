import { Injectable } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cartItem: CartItem[] = [];
  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() {}
  addToCart(theCartItem: CartItem) {
    //check if already
    let alreadyExistsInCart = false;
    let existingCartItem: CartItem | undefined = undefined;
    if (this.cartItem.length > 0) {
      //find the item in the cart
      for (const tempCartItem of this.cartItem) {
        if (tempCartItem.id === theCartItem.id) {
          existingCartItem = tempCartItem;

          break;
        }
      }
      //check if we found it
      alreadyExistsInCart = existingCartItem != undefined;
    }
    if (alreadyExistsInCart) {
      existingCartItem!.quantity++;
    } else {
      this.cartItem.push(theCartItem);
    }
    this.computeCartTotals();
  }

  private computeCartTotals() {
    let totalPriceValue = 0;
    let totalQuantityValue = 0;
    for (const currentCartItem of this.cartItem) {
      totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice!;
      totalQuantityValue += currentCartItem.quantity;
    }
    //publish values
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
    this.logCardDate(totalPriceValue, totalQuantityValue);
  }

  private logCardDate(totalPriceValue: number, totalQuantityValue: number) {
    console.log('Content of the cart');
    for (const tempCartItem of this.cartItem) {
      const subtotalPrice = tempCartItem.quantity * tempCartItem.unitPrice!;
      console.log(
        `name: ${tempCartItem.name},
         quantity=${tempCartItem.quantity},
         unitPrice=${tempCartItem.unitPrice} ,
         subtotalPrice=${subtotalPrice}`,
      );
    }
    console.log(
      `totalPrice: ${totalPriceValue.toFixed(2)}, totalQuantity:${totalQuantityValue}`,
    );
    console.log('----------------------------------');
  }
}
