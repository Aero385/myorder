package com.example.myorder.service;

import com.example.myorder.entity.Customer;
import com.example.myorder.entity.Order;
import com.example.myorder.entity.Product;
import com.example.myorder.repository.CustomerRepository;
import com.example.myorder.repository.OrderRepository;
import com.example.myorder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping
    public Order crateOrder(@RequestBody Order order,
                                     @RequestParam Integer customerId,
                                     @RequestParam Integer productId,
                                       @RequestParam int quantity) {
        Customer customer = customerRepository.findById(customerId).get();
        Product product = productRepository.findById(productId).get();
        order.setCustomer(customer);
        order.setQuantity(quantity);
        order.setProduct(product);
        return orderRepository.save(order);

    }
    @PostMapping
    public Order complete(@RequestParam Integer id){
      Order order = orderRepository.findById(id).get();
      order.setCompleted(!order.isCompleted());
      return orderRepository.save(order);
    }

    }

