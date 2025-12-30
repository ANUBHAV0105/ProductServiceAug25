package com.projects.productserviceaug25.controlleradvices;

import com.projects.productserviceaug25.exceptions.ProductnotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {
    public ResponseEntity<Void> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductnotFoundException.class)
    public ResponseEntity<String> handleProductnotFoundException(ProductnotFoundException productnotFoundException) {
        return new ResponseEntity<>(
                productnotFoundException.getProductId() + " is an invalid id", HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException npe) {
        return new ResponseEntity<>("Please try again with a valid product id", HttpStatus.NOT_FOUND);
    }
}
