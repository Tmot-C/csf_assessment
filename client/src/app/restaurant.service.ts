import { HttpClient } from "@angular/common/http"
import { inject, Injectable } from "@angular/core"
import { MenuItem, Order } from "./models"
import { Observable } from "rxjs"

@Injectable()
export class RestaurantService{ 

  private http = inject(HttpClient)
  
  // TODO: Task 2.2
  // You change the method's signature but not the name
  getMenus(): Observable<MenuItem[]> {
    return this.http.get<MenuItem[]>('/api/menu')
  }
  
  // TODO: Task 3.2

  placeOrder(order: Order) {
    // TODO Task 3
    return this.http.post<Order>('/api/food_order', order);
  }

}
