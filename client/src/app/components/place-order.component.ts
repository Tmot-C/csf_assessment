import { Component, inject, OnInit } from '@angular/core';
import { MenuItem } from '../models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-place-order',
  standalone: false,
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit {

  orderItems: MenuItem[] = [];
  totalCost: number = 0;
  totalItems: number = 0;

  private router = inject(Router);

  ngOnInit(): void {
    // Get the navigation state from router
    const navigation = this.router.getCurrentNavigation();
    const state = navigation.extras.state as {
      orderItems: MenuItem[], 
      totalCost: number,
      totalItems: number
    };

    this.orderItems = state.orderItems;
    this.totalCost = state.totalCost;
    this.totalItems = state.totalItems;
    }
  

    getItemSubtotal(item: MenuItem): number {
      return item.quantity * item.price;
    }

  

}
