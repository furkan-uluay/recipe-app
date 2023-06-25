package com.furkanuluay.recipeservice.exception;

/**
 * @author furkan
 */

public class RecipeAlreadyExistException extends RuntimeException {
  private String message;

  public RecipeAlreadyExistException(String message) {
    super(message);
    this.message = message;
  }

  public RecipeAlreadyExistException() {
    super();
  }
}
