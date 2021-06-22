import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  // @Input() data: number = 0;

  data: any[] = [];
  cartNumber: number = 0;
  totalCart: number = 0;
  cartItem: any[] = [];

  isUser: boolean = true;
  username!: string;
  userRoles!: string;
  email!: string;
  phone!: string;
  address!: string;
  avatar!: string;
  balance!: string;
  dob!: string;

  constructor(private authService: AuthService) {
    this.authService.cartSubject.subscribe((data) => {
      this.cartNumber = data;
    });
    this.authService.cartItem.subscribe((data) => {
      this.data = data;
    });
    this.authService.totalCart.subscribe((data) => {
      this.totalCart = data;
    });
  }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('username')!;
    this.email = sessionStorage.getItem('email')!;
    this.phone = sessionStorage.getItem('phone')!;
    this.address = sessionStorage.getItem('address')!;
    this.avatar = sessionStorage.getItem('avatar')!;
    this.balance = sessionStorage.getItem('balance')!;
    this.dob = sessionStorage.getItem('dob')!;
    this.loadCart();
  }

  loadCart() {
    this.data = localStorage.getItem('carts')
      ? JSON.parse(localStorage.getItem('carts')!)
      : [];
    this.cartNumber = this.data.length;
    console.log(this.data);
  }

  updateCart() {
    localStorage.setItem('carts', JSON.stringify(this.data));
    this.data = localStorage.getItem('carts')
      ? JSON.parse(localStorage.getItem('carts')!)
      : [];
    this.cartNumberFunc();
    console.log(this.data);
  }

  cartNumberFunc() {
    var cartValue = localStorage.getItem('carts')
      ? JSON.parse(localStorage.getItem('carts')!)
      : [];
    this.totalCart = 0;
    cartValue.forEach((element: any) => {
      this.totalCart += element.product.productPriceOut * element.quantity;
    });
    this.cartItem = cartValue;
    this.cartNumber = cartValue.length;
    this.authService.cartSubject.next(this.cartNumber);
    this.authService.cartItem.next(this.cartItem);
    this.authService.totalCart.next(this.totalCart);
    console.log(this.cartNumber);
  }

  deleteCart(index: number) {
    this.data.splice(index, 1);
    this.updateCart();
  }

  isLogOut() {
    this.authService.logout();
  }

  loggedIn() {
    return this.authService.isLoggedIn();
  }

  isAdmin() {
    return this.authService.isAdmin();
  }
}
