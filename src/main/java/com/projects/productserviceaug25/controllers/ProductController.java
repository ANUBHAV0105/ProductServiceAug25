package com.projects.productserviceaug25.controllers;

import com.projects.productserviceaug25.exceptions.ProductnotFoundException;
import com.projects.productserviceaug25.models.Product;
import com.projects.productserviceaug25.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable Long productId) throws ProductnotFoundException {
        return productService.getProductById(productId);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) throws ProductnotFoundException {
        return productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId, @RequestBody Product product)
            throws ProductnotFoundException {
        return productService.replaceProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable("productId") Long productId) throws ProductnotFoundException {
        return productService.deleteProduct(productId);
    }
}
