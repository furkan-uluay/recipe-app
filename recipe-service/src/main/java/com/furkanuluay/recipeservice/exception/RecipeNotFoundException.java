package com.furkanuluay.recipeservice.exception;


/**
 * @author furkan
 */

public class RecipeNotFoundException extends RuntimeException {
  private String message;

  public RecipeNotFoundException(String message) {
    super(message);
    this.message = message;
  }

  public RecipeNotFoundException() {
    super();
  }
}
