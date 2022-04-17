package com.example.myorder.repository;

import com.example.myorder.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    public Long countById(Integer id);


}
