import { Component, inject, OnInit } from '@angular/core';
import { MenuItem, MenuItemMini, Order } from '../models';
import { Router } from '@angular/router';
import { MenuStore } from '../menu.store';
import { map, Observable } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RestaurantService } from '../restaurant.service';

@Component({
  selector: 'app-place-order',
  standalone: false,
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit {

  private router = inject(Router);
  private menuStore = inject(MenuStore);
  private rs = inject(RestaurantService);
  private fb = inject(FormBuilder);

  
  orderItems: MenuItem[] = [];
  orderForm!: FormGroup;

  ngOnInit(): void {
    
    this.orderForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
    
    this.menuStore.menuItems$.subscribe(items => {
      this.orderItems = items.filter(item => (item.quantity || 0) > 0);
    });
  }

  
  getItemSubtotal(item: MenuItem): number {
    return (item.quantity || 0) * item.price;
  }

  getTotalPrice(): number {
    return this.orderItems.reduce(
      (sum, item) => sum + this.getItemSubtotal(item),
      0
    );
  }

  startOver(): void {
    this.router.navigate(['']);
  }

  
  placeOrder(): void {

    const items: MenuItemMini[] = this.orderItems.map(item => ({
      id: item.id,
      price: item.price,
      quantity: item.quantity
    }));


    const order: Order = {
      username: this.orderForm.value.username,      // from the form
      password: this.orderForm.value.password,  // from the form
      items
    };

    // 4) Send the order to backend
    this.rs.placeOrder(order).subscribe({
      next: (response) => {
        console.log('Order submitted successfully!', response);
      },
      error: (error) => {
        console.error('Error submitting order:', error);
      }
    });
  }

}
