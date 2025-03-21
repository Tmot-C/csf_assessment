import { Component, inject, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant.service';
import { map, Observable, Subscription, tap } from 'rxjs';
import { MenuItem } from '../models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit  {

  private rs = inject(RestaurantService);
  private router = inject(Router);

  menus$!: Observable<MenuItem[]>
  private menuItems: MenuItem[] = [];
  private menuSubscription!: Subscription;
  

  itemQuantities: { [id: string]: number } = {};
  
  ngOnInit(): void {
    // Get menu items and initialize quantity to 0 if undefined
    this.menus$ = this.rs.getMenus().pipe(
      map((items: MenuItem[]) => {
        return items.map(item => {
          return {
            ...item,
            quantity: item.quantity || 0 // Initialize to 0 if undefined
          };
        });
      }),
      tap(items => {
        this.menuItems = items;
      })
    );
    
    // Subscribe once to populate our local array
    this.menuSubscription = this.menus$.subscribe();
  }

  ngOnDestroy(): void {
    // Clean up subscription when component is destroyed
    if (this.menuSubscription) {
      this.menuSubscription.unsubscribe();
    }
  }


  increaseQuantity(item: MenuItem): void {
    item.quantity = (item.quantity || 0) + 1;
  }
  
  decreaseQuantity(item: MenuItem): void {
    if (item.quantity && item.quantity > 0) {
      item.quantity -= 1;
    }
  }
  
  // Calculate totals directly from menuItems array
  getTotalItems(): number {
    return this.menuItems.reduce(
      (sum, item) => sum + (item.quantity || 0), 
      0
    );
  }
  
  getTotalCost(): number {
    return this.menuItems.reduce(
      (sum, item) => sum + ((item.quantity || 0) * item.price), 
      0
    );
  }

  placeOrder(): void {
    // Filter items with quantity > 0
    const orderedItems = this.menuItems.filter(
      item => item.quantity && item.quantity > 0
    );
    
    if (orderedItems.length > 0) {
      //https://angular.dev/api/router/NavigationExtras
      this.router.navigate(['/food_order'], { 
        state: { 
          orderItems: orderedItems,
          totalCost: this.getTotalCost(),
          totalItems: this.getTotalItems()
        } 
      });
    }
  }
  
}
