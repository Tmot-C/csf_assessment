package vttp.batch5.csf.assessment.server.utils;

import vttp.batch5.csf.assessment.server.models.MenuItem;
import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;


public class Utils {

    public static MenuItem toMenuItem (Document doc) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(doc.getString("_id"));
        menuItem.setName(doc.getString("name"));
        menuItem.setPrice(doc.getDouble("price").floatValue());
        menuItem.setDescription(doc.getString("description"));
        
        return menuItem;
    }

    public static JsonObject menuToJson(MenuItem menuItem) {
    return Json.createObjectBuilder()
      .add("id", menuItem.getId())
      .add("name", menuItem.getName())
      .add("description", menuItem.getDescription())
      .add("price", menuItem.getPrice())
      .build();
  }
    
}
