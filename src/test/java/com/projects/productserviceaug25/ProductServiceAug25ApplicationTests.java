package com.projects.productserviceaug25;

import com.projects.productserviceaug25.models.Product;
import com.projects.productserviceaug25.projections.ProductWithTiltleAndPrice;
import com.projects.productserviceaug25.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceAug25ApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testQuery() {
        List<ProductWithTiltleAndPrice> productWithTiltleAndPrices = productRepository.findTitleAndPriceById();
        for (ProductWithTiltleAndPrice productWithTiltleAndPrice : productWithTiltleAndPrices) {
            System.out.println(productWithTiltleAndPrice.getTitle() + "------>" + productWithTiltleAndPrice.getPrice());
        }
        Optional<Product> optionalProduct = productRepository.findByCategory_Title("mobile");
        System.out.println(optionalProduct.get().getPrice());
    }

}
