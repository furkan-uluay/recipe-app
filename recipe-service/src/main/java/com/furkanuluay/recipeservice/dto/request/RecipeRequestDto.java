package com.furkanuluay.recipeservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class RecipeRequestDto {

  @NotEmpty(message = "Title is required")
  @Size(max = 100, message = "Title cannot exceed 100 characters")
  private String title;

  @NotEmpty(message = "At least one category must be specified")
  private List<CategoryRequestDto> categories;

  @NotEmpty(message = "Yield is required")
  private String yield;

  @NotEmpty(message = "At least one ingredient must be specified")
  @Valid
  private List<@Valid IngredientDto> ingredients;

  @NotEmpty(message = "Directions are required")
  private String directions;

  @Data
  public static class IngredientDto {
    @NotEmpty(message = "Amount is required")
    private String amount;

    private String unit;

    @NotEmpty(message = "Item is required")
    private String item;
  }
}
