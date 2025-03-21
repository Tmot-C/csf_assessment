// You may use this file to create any models
export interface MenuItem {
    id: string
    name: string
    description: string
    price: number
    quantity: number
  }

  export interface MenuItemMini {
    id: string
    price: number
    quantity: number
  }
  
  export interface Order {
    username: string
    password: string
    items: MenuItemMini[];
  }