package vttp.batch5.csf.assessment.server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch5.csf.assessment.server.models.Customer;

// Use the following class for MySQL database
@Repository
public class RestaurantRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String sql = "SELECT username, password FROM customers WHERE username = ?";

    final String sql2 = "INSERT INTO payments " +
    "(payment_id, order_id, username, total, timestamp, items) " +
    "VALUES (?, ?, ?, ?, ?, ?)";
    public Optional<Customer> findByUsername(String username) {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, username);
        
        if (rs.next()) {
            Customer customer = new Customer();
            customer.setUsername(rs.getString("username"));
            customer.setPassword(rs.getString("password"));
            return Optional.of(customer);
        }
        
        return Optional.empty();
    }
}


