package com.furkanuluay.recipeservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Furkan Uluay
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponseDto {
  private Long id;
  private String title;
  private List<String> categories;
  private String yield;
  private List<IngredientDto> ingredients;
  private String directions;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class IngredientDto {
    private String amount;
    private String unit;
    private String item;
  }
}
