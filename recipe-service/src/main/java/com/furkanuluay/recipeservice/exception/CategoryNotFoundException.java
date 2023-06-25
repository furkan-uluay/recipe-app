package com.furkanuluay.recipeservice.exception;


/**
 * @author furkan
 */

public class CategoryNotFoundException extends RuntimeException {
    private String message;

    public CategoryNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public CategoryNotFoundException() {
        super();
    }
}

