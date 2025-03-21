package vttp.batch5.csf.assessment.server.services;

import java.io.IOException;
import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import vttp.batch5.csf.assessment.server.models.Customer;
import vttp.batch5.csf.assessment.server.models.MenuItem;
import vttp.batch5.csf.assessment.server.models.Order;
import vttp.batch5.csf.assessment.server.models.PaymentRequest;
import vttp.batch5.csf.assessment.server.repositories.OrdersRepository;
import vttp.batch5.csf.assessment.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {

  @Autowired
  private OrdersRepository ordersRepo;

  @Autowired
  private RestaurantRepository restRepo;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final RestTemplate restTemplate = new RestTemplate();

  // TODO: Task 2.2
  // You may change the method's signature
  public List<MenuItem> getMenu() {
    List<MenuItem> menus = ordersRepo.getMenu();
    Collections.sort(menus, Comparator.comparing(MenuItem::getName));
    return menus;
    
  }
  
  // TODO: Task 4
  public boolean validateOrder(String payload) {

    Order order = getOrder(payload);

    Optional<Customer> opt = restRepo.findByUsername(order.getUsername());

    String hashedPw = encoder(order.getPassword());

    if (!opt.isPresent()) {
      return false;
  }
    Customer customer = opt.get();

    return hashedPw.equalsIgnoreCase(customer.getPassword()); 

  }

   public String postPayment(float totalPrice, Order validatedOrder, String orderId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        headers.set("X-Authenticate", validatedOrder.getUsername());

        PaymentRequest requestBody = new PaymentRequest();
        requestBody.setOrder_id(orderId);                  
        requestBody.setPayer(validatedOrder.getUsername()); 
        requestBody.setPayee(validatedOrder.getUsername());                        
        requestBody.setPayment(totalPrice);                 

        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(requestBody, headers);

        String paymentUrl = "https://payment-service-production-a75a.up.railway.app/api/payment";

        String response = restTemplate.postForObject(paymentUrl, requestEntity, String.class);

        return response;
    }

  public Order getOrder(String payload){
    Order order;
      try {
        order = objectMapper.readValue(payload, Order.class);
        return order;
      } catch (IOException e) {
          e.printStackTrace();
          return null;
      }
  }

  public static String encoder(String input) 
  { 
      try { 
          
          MessageDigest md = MessageDigest.getInstance("SHA-224"); 

          byte[] messageDigest = md.digest(input.getBytes()); 


          BigInteger no = new BigInteger(1, messageDigest); 


          String hashtext = no.toString(16); 


          while (hashtext.length() < 32) { 
              hashtext = "0" + hashtext; 
          } 

          return hashtext; 
      } 

      catch (NoSuchAlgorithmException e) { 
          throw new RuntimeException(e); 
      } 
  }

}
