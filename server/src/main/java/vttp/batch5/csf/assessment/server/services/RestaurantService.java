package vttp.batch5.csf.assessment.server.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.csf.assessment.server.models.MenuItem;
import vttp.batch5.csf.assessment.server.repositories.OrdersRepository;

@Service
public class RestaurantService {

  @Autowired
  private OrdersRepository ordersRepo;


  // TODO: Task 2.2
  // You may change the method's signature
  public List<MenuItem> getMenu() {
    List<MenuItem> menus = ordersRepo.getMenu();
    Collections.sort(menus, Comparator.comparing(MenuItem::getName));
    return menus;
    
  }
  
  // TODO: Task 4


}
