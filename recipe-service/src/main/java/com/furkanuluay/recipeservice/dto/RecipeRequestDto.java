package com.furkanuluay.recipeservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

/**
 * @author Furkan Uluay
 */
@Data
public class RecipeRequestDto {
  @NotBlank(message = "Title is required")
  @Size(max = 100, message = "Title cannot exceed 100 characters")
  private String title;

  @NotNull(message = "Categories are required")
  @Size(min = 1, message = "At least one category must be specified")
  private List<@NotBlank(message = "Category cannot be blank") String> categories;

  @NotBlank(message = "Yield is required")
  private String yield;

  @NotNull(message = "Ingredients are required")
  @Size(min = 1, message = "At least one ingredient must be specified")
  @Valid
  private List<@NotNull(message = "Ingredient cannot be null") @Valid IngredientDto> ingredients;

  @NotBlank(message = "Directions are required")
  private String directions;

  @Data
  public static class IngredientDto {
    @NotBlank(message = "Amount is required")
    private String amount;

    private String unit;

    @NotBlank(message = "Item is required")
    private String item;

  }
}
