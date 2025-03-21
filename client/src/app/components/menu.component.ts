import { Component, Inject, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant.service';
import { Observable } from 'rxjs';
import { menuItem } from '../models';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit  {

  private rs = Inject(RestaurantService)
  menus$!: Observable<menuItem[]>
  
  ngOnInit(): void {

    this.menus$ = this.rs.getMenus()
    
  }
  
}
