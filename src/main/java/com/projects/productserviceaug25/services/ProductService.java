package com.projects.productserviceaug25.services;

import com.projects.productserviceaug25.exceptions.ProductnotFoundException;
import com.projects.productserviceaug25.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long productId) throws ProductnotFoundException;

    Product createProduct(Product product) throws ProductnotFoundException;

    Product replaceProduct(Long productid, Product product) throws ProductnotFoundException;

    Product deleteProduct(Long productId) throws ProductnotFoundException;

}
