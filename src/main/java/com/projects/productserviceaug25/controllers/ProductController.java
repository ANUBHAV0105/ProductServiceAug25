package com.projects.productserviceaug25.controllers;

import com.projects.productserviceaug25.commons.AuthCommons;
import com.projects.productserviceaug25.exceptions.ProductnotFoundException;
import com.projects.productserviceaug25.models.Product;
import com.projects.productserviceaug25.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}/{tokenValue}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId, @PathVariable("tokenValue") String tokenValue) throws ProductnotFoundException {
        Product product = null;
        ResponseEntity<Product> responseEntity = null;
        if (AuthCommons.validateToken(tokenValue)){
            product =  productService.getProductById(productId);
            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>(product,HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
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
    @GetMapping("/title/{title}/{pageNumber}/{pageSize}")
    public Page<Product> getProductsByTitle(
            @PathVariable("title") String title,
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize) {

        return productService.getProductsByTitle(title, pageNumber, pageSize);
    }
}
