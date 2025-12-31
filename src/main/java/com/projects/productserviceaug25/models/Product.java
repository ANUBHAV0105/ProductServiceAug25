package com.projects.productserviceaug25.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    @Column(nullable=false, unique=true)
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne
//    @JoinColumn(name = "category_id")
    private Category category;

}
