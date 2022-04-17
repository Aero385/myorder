package com.example.myorder.repository;

import com.example.myorder.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository <Order, Integer> {
}
