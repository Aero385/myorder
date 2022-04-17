package com.example.myorder.service;

import com.example.myorder.entity.Customer;
import com.example.myorder.exception.CustomerNotFoundException;
import com.example.myorder.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    public List<Customer> listAll(){
        return (List<Customer>) customerRepo.findAll();
    }

    public void save(Customer customer) {
        customerRepo.save(customer);

    }

    public Customer get(Integer id) throws CustomerNotFoundException {
        Optional<Customer> result = customerRepo.findById(id);
        if(result.isPresent()){
            return result.get();

        }throw new CustomerNotFoundException("Could not find any customer with ID " + id);

    }

    public void deleteCustomer(Integer id) throws CustomerNotFoundException {
        Long count = customerRepo.countById(id);
        if (count == null || count == 0) {
            throw new CustomerNotFoundException("Could not find any customer with ID " +id);
        }
        customerRepo.deleteById(id);
    }
}




