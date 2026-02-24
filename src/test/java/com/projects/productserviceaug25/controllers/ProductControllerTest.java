package com.projects.productserviceaug25.controllers;

import com.projects.productserviceaug25.exceptions.ProductnotFoundException;
import com.projects.productserviceaug25.models.Product;
import com.projects.productserviceaug25.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockitoBean
    private ProductService productService;
    @Test
    public void testSingleProductPositive() throws ProductnotFoundException {
        Long productId = 10L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setTitle("iPhone 14 pro.");
        expectedProduct.setDescription("iPhone 14 pro.");
        expectedProduct.setPrice(130000.0);
        when(productService.getProductById(productId)).thenReturn(expectedProduct);
        Product actualproduct = productController.getSingleProduct(productId,"").getBody();
        assertEquals(expectedProduct,actualproduct,"Products are not equal");
    }

    @Test
    public void testGetSingleProductInvalidId() throws ProductnotFoundException {
        Long productId = 10L;
        when(productService.getProductById(productId)).thenThrow(new ProductnotFoundException());

        assertThrows(ProductnotFoundException.class, () -> productController.getSingleProduct(productId,""));
    }
    @Test
    public void testGetAllProducts() throws ProductnotFoundException {
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        List<Product> products = List.of(product1,product2,product3);
        when(productService.getAllProducts()).thenReturn(products);
        List<Product> actualproducts = productController.getAllProducts();
        assertArrayEquals(products.toArray(),actualproducts.toArray());
    }
}