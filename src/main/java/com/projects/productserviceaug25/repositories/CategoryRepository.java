package com.projects.productserviceaug25.repositories;

import com.projects.productserviceaug25.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByTitle(String title);

    Category save(Category category);
}
