package com.example.myorder.controller;

import com.example.myorder.entity.Customer;
import com.example.myorder.entity.Product;
import com.example.myorder.exception.CustomerNotFoundException;
import com.example.myorder.exception.ProductNotFoundException;
import com.example.myorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String showCustomerList(Model model){
        List<Customer> listCustomers = customerService.listAll();
        model.addAttribute("listCustomers", listCustomers);
        return "customers";
    }

    @GetMapping("/customers/new")
    public String showNewForm(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Add New Customer");
        return "customer_form";
    }

    @GetMapping("/customers/save")
    public String saveCustomer(Customer customer, RedirectAttributes ra) {
        customerService.save(customer);
        ra.addFlashAttribute("message", "The customer was successfully saved");
        return "redirect:/customers";
    }

    @GetMapping("customers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Customer customer = customerService.get(id);
            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Edit product (ID: " + id + ")");
            return "customer_form";
        }catch (CustomerNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/customers";
        }
    }
    @GetMapping("customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            customerService.deleteCustomer(id);

        } catch (CustomerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/customers";

    }
}
