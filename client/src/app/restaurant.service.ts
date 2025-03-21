import { HttpClient } from "@angular/common/http"
import { inject, Injectable } from "@angular/core"
import { menuItem } from "./models"
import { Observable } from "rxjs"

@Injectable()
export class RestaurantService{ 

  private http = inject(HttpClient)
  
  // TODO: Task 2.2
  // You change the method's signature but not the name
  getMenu(): Observable<menuItem[]> {
    return this.http.get<menuItem[]>('/api/menu')
  }
  
  // TODO: Task 3.2
}
