package com.example.myorder.service;


import com.example.myorder.entity.Product;
import com.example.myorder.exception.ProductNotFoundException;
import com.example.myorder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    public List<Product> listAll(){
        return (List<Product>) productRepo.findAll();
    }

    public void save(Product product) {
        productRepo.save(product);

    }
    
    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> result = productRepo.findById(id);
        if(result.isPresent()){
            return result.get();

        }throw new ProductNotFoundException("Could not find any products with ID " + id);

    }

    public void deleteProduct(Integer id) throws ProductNotFoundException {
        Long count = productRepo.countById(id);
        if (count == null || count == 0) {
        throw new ProductNotFoundException("Could not find any product with ID " +id);
        }
        productRepo.deleteById(id);
    }
}
