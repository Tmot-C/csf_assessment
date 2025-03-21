package vttp.batch5.csf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch5.csf.assessment.server.models.MenuItem;
import vttp.batch5.csf.assessment.server.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


@Repository
public class OrdersRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  // TODO: Task 2.2
  // You may change the method's signature
  // Write the native MongoDB query in the comment below
  //
  //  db.collection.find({})
  //
  public List<MenuItem> getMenu() {
    System.out.println("check2");
    List<Document> documents = mongoTemplate.findAll(Document.class, "menus");
    List<MenuItem> menuItems = new ArrayList<>();
    for (Document doc : documents) {
      System.out.println(Utils.toMenuItem(doc).toString());
      menuItems.add(Utils.toMenuItem(doc));
      
    }
    return menuItems;

  }

  // TODO: Task 4
  // Write the native MongoDB query for your access methods in the comment below
  //
  //  Native MongoDB query here
  
}
