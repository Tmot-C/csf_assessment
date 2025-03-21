package vttp.batch5.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.csf.assessment.server.services.RestaurantService;
import vttp.batch5.csf.assessment.server.utils.Utils;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

  @Autowired
  private RestaurantService restSvc;

  // TODO: Task 2.2
  // You may change the method's signature
  @GetMapping(path="/menu")
  @ResponseBody
  public ResponseEntity<String> getMenus() {
    System.out.println("check1");
    List<JsonObject> menus = restSvc.getMenu().stream()
    .map(Utils::menuToJson)
    .toList();
    
    return ResponseEntity.ok(Json.createArrayBuilder(menus).build().toString());
  }

  // TODO: Task 4
  // Do not change the method's signature
  public ResponseEntity<String> postFoodOrder(@RequestBody String payload) {
    return ResponseEntity.ok("{}");
  }
}
