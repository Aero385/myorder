package com.example.myorder.controller;


import com.example.myorder.entity.Product;
import com.example.myorder.exception.ProductNotFoundException;
import com.example.myorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    @Autowired private ProductService productService;

@GetMapping("/products")
    public String showProductList(Model model){
    List<Product> listProducts = productService.listAll();
    model.addAttribute("listProducts", listProducts);
    return "products";

}

@GetMapping("/products/new")
    public String showNewForm(Model model){
    model.addAttribute("product", new Product());
    model.addAttribute("pageTitle1", "Add New Product");
    return "product_form";
}

@PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes ra){
    productService.save(product);
    ra.addFlashAttribute("message1", "The product has been saved successfully.");
    return "redirect:/products";
}

@GetMapping ("products/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
    try {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle1", "Edit product (ID: " + id + ")");
        return "product_form";
    } catch (ProductNotFoundException e) {
        ra.addFlashAttribute("message1", e.getMessage());
        return "redirect:/products";
    }
}

    @GetMapping("products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            productService.deleteProduct(id);

        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";

    }
}
