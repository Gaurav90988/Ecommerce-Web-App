package com.example.Ecomm.controller;

import com.example.Ecomm.model.Product;
import com.example.Ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public  Product getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product)
    {
      return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void  deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }
}
