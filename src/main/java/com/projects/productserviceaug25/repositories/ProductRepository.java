package com.projects.productserviceaug25.repositories;

import com.projects.productserviceaug25.models.Category;
import com.projects.productserviceaug25.models.Product;
import com.projects.productserviceaug25.projections.ProductWithTiltleAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByTitle(String title);
    List<Product> findByTitleContainsIgnoreCase(String str);

    // select * from products where price >= start and price <= end
    List<Product> findByPriceBetween(Double start, Double end);


    //select * from products where title LIKE '%str%' and price >= start and price <= end
    List<Product> findByTitleContainsIgnoreCaseAndPriceBetween(String title, Double start, Double end);

    List<Product> findByCreatedAtBetween(Date start, Date end);

    @Override
    void deleteById(Long aLong);

    Product save(Product product);

    //Query : Find the title and price of the product with id = 10;
    // select title, price from products where id = 10;
    @Query(value = "select p.title, p.price from products p where p.id = 2", nativeQuery = true)
    List<ProductWithTiltleAndPrice> findTitleAndPriceById();


    Optional<Product> findByCategory(Category category);

    Optional<Product> findByCategory_Title(String title);



//CRUD => Read & Delete
/// Create & Update

}
