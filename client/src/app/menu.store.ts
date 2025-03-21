import { ComponentStore } from "@ngrx/component-store";
import { MenuItem} from "./models";
import { Injectable } from "@angular/core";

export interface MenuItemsState {
    MenuItemsSlice: MenuItem[],
}

@Injectable({
    providedIn: 'root'
})


export class MenuStore extends ComponentStore<MenuItemsState>{

    constructor(){
        super ({  MenuItemsSlice: []});
    }

    //Selector
    readonly menuItems$ = this.select(state => state.MenuItemsSlice);
    
    //Updaters
    readonly setMenuItems = this.updater((state, menuItems: MenuItem[]) => ({
        ...state,
        MenuItemsSlice: menuItems
      }));


}