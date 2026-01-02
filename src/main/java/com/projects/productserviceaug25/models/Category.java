package com.projects.productserviceaug25.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "category")
public class Category extends BaseModel{
    private String title;
}
