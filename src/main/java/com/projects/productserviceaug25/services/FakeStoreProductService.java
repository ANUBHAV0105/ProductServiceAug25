package com.projects.productserviceaug25.services;

import com.projects.productserviceaug25.dtos.FakeStoreProductDto;
import com.projects.productserviceaug25.models.Category;
import com.projects.productserviceaug25.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate
                .getForEntity("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);
        FakeStoreProductDto[] products = responseEntity.getBody();
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : products) {
            productList.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product getProductById(Long productId) {
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate
                .getForEntity("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(responseEntity.getBody());
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto requestDto = convertProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate
                .postForEntity("https://fakestoreapi.com/products/", requestDto, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(responseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productid, Product product) {
        FakeStoreProductDto requestDto = convertProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productid,
                HttpMethod.PUT, new HttpEntity<>(requestDto), FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(responseEntity.getBody());
    }

    public Product deleteProduct(Long productId) {
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.DELETE, null, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(responseEntity.getBody());
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        if (product == null) {
            return null;
        }

        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImageUrl());
        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getTitle());
        }
        dto.setId(product.getId());

        return dto;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }

        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setId(fakeStoreProductDto.getId());

        return product;
    }

}
