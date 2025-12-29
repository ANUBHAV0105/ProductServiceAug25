package com.projects.productserviceaug25.services;

import com.projects.productserviceaug25.models.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(Product product);
    Product replaceProduct(Long productid,Product product);
    Product deleteProduct(Long productId);


}
