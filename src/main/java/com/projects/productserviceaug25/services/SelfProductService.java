package com.projects.productserviceaug25.services;

import com.projects.productserviceaug25.exceptions.ProductnotFoundException;
import com.projects.productserviceaug25.models.Category;
import com.projects.productserviceaug25.models.Product;
import com.projects.productserviceaug25.repositories.CategoryRepository;
import com.projects.productserviceaug25.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) throws ProductnotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductnotFoundException(productId);
        }
        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) throws ProductnotFoundException {
        // Handle category persistence to avoid TransientPropertyValueException
        if (product.getCategory() != null) {
            String categoryTitle = product.getCategory().getTitle();

            // Check if category already exists in database
            Optional<Category> existingCategory = categoryRepository.findByTitle(categoryTitle);

            if (existingCategory.isPresent()) {
                // Reuse existing category
                product.setCategory(existingCategory.get());
            } else {
                // Save new category first
                Category savedCategory = categoryRepository.save(product.getCategory());
                product.setCategory(savedCategory);
            }
        }

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productid, Product product) throws ProductnotFoundException {
        // Check if product exists
        Optional<Product> existingProductOptional = productRepository.findById(productid);
        if (existingProductOptional.isEmpty()) {
            throw new ProductnotFoundException(productid);
        }

        // Get the existing product
        Product existingProduct = existingProductOptional.get();

        // Update fields
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImageUrl(product.getImageUrl());

        // Handle category persistence (similar to createProduct)
        if (product.getCategory() != null) {
            String categoryTitle = product.getCategory().getTitle();

            // Check if category already exists in database
            Optional<Category> existingCategory = categoryRepository.findByTitle(categoryTitle);

            if (existingCategory.isPresent()) {
                // Reuse existing category
                existingProduct.setCategory(existingCategory.get());
            } else {
                // Save new category first
                Category savedCategory = categoryRepository.save(product.getCategory());
                existingProduct.setCategory(savedCategory);
            }
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductnotFoundException {
        // Check if product exists
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductnotFoundException(productId);
        }

        Product product = productOptional.get();

        // Delete the product
        productRepository.delete(product);

        return product;
    }
}
