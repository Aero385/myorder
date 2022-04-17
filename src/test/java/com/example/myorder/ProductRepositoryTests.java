package com.example.myorder;


import com.example.myorder.entity.Product;
import com.example.myorder.repository.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

    @Autowired private ProductRepository repo;

    @Test
    public void testAddNew(){
        Product product = new Product();
        product.setName("spoon");
        product.setUnitPrice(9);
        product.setSkuCode(785164);

        Product savedProduct = repo.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);


    }

    @Test
    public void testAListAll(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);

        for(Product product : products){
            System.out.println(product);
        }
    }

    @Test
    public void testUpdate(){
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();
        product.setSkuCode(456887);
        repo.save(product);

        Product updateProduct = repo.findById(productId).get();
        Assertions.assertThat(updateProduct.getSkuCode()).isEqualTo(456887);


    }

    @Test
    public void testGet(){
        Integer productId = 2;
        Optional<Product> optionalProduct = repo.findById(productId);


        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct);

    }

    @Test
    public void testDelete(){
        Integer productId = 2;
        repo.deleteById(productId);

        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();

    }
}
