package vttp.batch5.csf.assessment.server.models;

public class MenuItem {
    private String id;
    private String name;
    private String description;
    private float price;

    public MenuItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return Math.round(price * 100.0f) / 100.0f; 
    }

    public void setPrice(float price) {
        this.price = Math.round(price * 100.0f) / 100.0f;
    }

    



    
    
    
}
