package com.example.myorder.repository;

import com.example.myorder.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer, Integer> {

    public Long countById(Integer id);
}
