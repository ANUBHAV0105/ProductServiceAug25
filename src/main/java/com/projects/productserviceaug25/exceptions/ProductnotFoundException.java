package com.projects.productserviceaug25.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ProductnotFoundException extends Exception {

    @Getter
    @Setter
    private Long productId;
    public ProductnotFoundException(Long productId) {
        this.productId = productId;
    }

}
