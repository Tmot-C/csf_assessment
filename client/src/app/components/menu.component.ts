import { Component, inject, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant.service';
import { map, Observable, Subscription, tap } from 'rxjs';
import { MenuItem } from '../models';
import { Router } from '@angular/router';
import { MenuStore } from '../menu.store';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit  {


  private rs = inject(RestaurantService);
  private router = inject(Router);
  private menuStore = inject(MenuStore);

  
  menus$ = this.menuStore.menuItems$;
  private menuSubscription!: Subscription;


  private cachedItems: MenuItem[] = [];

  itemQuantities: { [id: string]: number } = {};
  
  ngOnInit(): void {

    this.menuSubscription = this.rs.getMenus().pipe(
      map((items: MenuItem[]) =>
        items.map(item => ({
          ...item,
          quantity: item.quantity || 0
        }))
      ),
      tap(items => {

        this.menuStore.setMenuItems(items);
      })
    ).subscribe();

    this.menuStore.menuItems$.subscribe(items => {
      this.cachedItems = items;
    });
  }

  ngOnDestroy(): void {

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

  getTotalItems(): number {
    return this.cachedItems.reduce((sum, item) => sum + (item.quantity || 0), 0);
  }
  
  getTotalCost(): number {
    return this.cachedItems.reduce((sum, item) => 
      sum + ((item.quantity || 0) * item.price), 
      0
    );
  }

  placeOrder(): void {
    // Filter items with quantity > 0
    const orderedItems = this.cachedItems.filter(item => (item.quantity || 0) > 0);
    
    if (orderedItems.length > 0) {
      
      this.router.navigate(['/food_order']);
    }
  }
  
  
}
