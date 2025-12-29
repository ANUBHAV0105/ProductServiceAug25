package com.projects.productserviceaug25.controllers;

import com.projects.productserviceaug25.models.Product;
import com.projects.productserviceaug25.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable Long productId){
        return productService.getProductById(productId);
    }
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId,@RequestBody Product product){
        return productService.replaceProduct(productId,product);
    }
    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable("productId") Long productId){
        return productService.deleteProduct(productId);
    }
}
