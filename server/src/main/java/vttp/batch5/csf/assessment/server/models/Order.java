package vttp.batch5.csf.assessment.server.models;

import java.util.List;

public class Order {

    private String username;
    private String password;
    private List<Items> items;
    public Order() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Items> getItems() {
        return items;
    }
    public void setItems(List<Items> items) {
        this.items = items;
    }
    
    public float getTotalPrice() {
        if (items == null) {
            return 0.0f;
        }
    
        float total = 0.0f;
        for (Items item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    

    
    
}
