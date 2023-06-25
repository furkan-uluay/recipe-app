package com.furkanuluay.recipeservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.furkanuluay.recipeservice.controller.RecipeController;
import com.furkanuluay.recipeservice.domain.Ingredient;
import com.furkanuluay.recipeservice.domain.Recipe;
import com.furkanuluay.recipeservice.dto.RecipeResponseDto;
import com.furkanuluay.recipeservice.exception.CategoryNotFoundException;
import com.furkanuluay.recipeservice.exception.RecipeNotFoundException;
import com.furkanuluay.recipeservice.mapper.RecipeMapper;
import com.furkanuluay.recipeservice.service.RecipeService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RecipeControllerTest {

  @Mock RecipeMapper recipeMapper;
  @Mock private RecipeService recipeService;
  @InjectMocks private RecipeController recipeController;

  private Recipe recipeTest1;
  private Recipe recipeTest2;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    loadRecipeTest1();
    loadRecipeTest2();
  }

  private void loadRecipeTest1() {
    // Test Data 1
    recipeTest1 = new Recipe();
    recipeTest1.setId(1L);
    recipeTest1.setTitle("title1");
    recipeTest1.setCategories(
        new ArrayList<>() {
          {
            add("category1_1");
            add("category1_1");
          }
        });
    recipeTest1.setYield("5");

    Ingredient ingredient1_1 = new Ingredient();
    ingredient1_1.setAmount("1");
    ingredient1_1.setUnit("ingredientUnit1_1");
    ingredient1_1.setItem("ingredientItem1_2");

    Ingredient ingredient1_2 = new Ingredient();
    ingredient1_2.setAmount("2");
    ingredient1_2.setUnit("ingredientUnit1_1");
    ingredient1_2.setItem("ingredientItem1_2");

    recipeTest1.setIngredients(
        new ArrayList<>() {
          {
            add(ingredient1_1);
            add(ingredient1_2);
          }
        });
    recipeTest1.setDirections("directions1");
  }

  private void loadRecipeTest2() {
    // Test Data 2
    recipeTest2 = new Recipe();
    recipeTest2.setId(1L);
    recipeTest2.setTitle("title2");
    recipeTest2.setCategories(
        new ArrayList<>() {
          {
            add("category2_1");
            add("category2_2");
          }
        });
    recipeTest2.setYield("3");

    Ingredient ingredient2_1 = new Ingredient();
    ingredient2_1.setAmount("2");
    ingredient2_1.setUnit("ingredientUnit2_1");
    ingredient2_1.setItem("ingredientItem2_1");

    Ingredient ingredient2_2 = new Ingredient();
    ingredient2_2.setAmount("2");
    ingredient2_2.setUnit("ingredientUnit2_2");
    ingredient2_2.setItem("ingredientItem2_2");

    recipeTest2.setIngredients(
        new ArrayList<>() {
          {
            add(ingredient2_1);
            add(ingredient2_2);
          }
        });
    recipeTest2.setDirections("directions2");
  }

  @Test
  public void testGetAllRecipes_WithExistingRecipes_ShouldReturnListOfRecipes() {
    List<Recipe> recipes = new ArrayList<>();
    recipes.add(recipeTest1);
    recipes.add(recipeTest2);

    when(recipeService.getAllRecipes()).thenReturn(recipes);

    RecipeResponseDto responseDto1 = new RecipeResponseDto();
    responseDto1.setId(recipeTest1.getId());

    RecipeResponseDto responseDto2 = new RecipeResponseDto();
    responseDto2.setId(recipeTest2.getId());

    when(recipeMapper.entityToDto(recipeTest1)).thenReturn(responseDto1);
    when(recipeMapper.entityToDto(recipeTest2)).thenReturn(responseDto2);

    ResponseEntity<List<RecipeResponseDto>> response = recipeController.getAllRecipes();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(2, response.getBody().size());

    assertEquals(responseDto1, response.getBody().get(0));
    assertEquals(responseDto1.getId(), response.getBody().get(0).getId());

    assertEquals(responseDto2, response.getBody().get(1));
    assertEquals(responseDto2.getId(), response.getBody().get(1).getId());
  }

  @Test
  public void testGetAllRecipes_WithNoRecipes_ShouldThrowRecipeNotFoundException() {
    // Arrange
    when(recipeService.getAllRecipes()).thenReturn(new ArrayList<>());

    // Act & Assert
    assertThrows(
        RecipeNotFoundException.class,
        () -> {
          recipeController.getAllRecipes();
        });
  }

  @Test
  public void testGetAllCategories_WithNoCategories_ShouldThrowCategoryNotFoundException() {

    when(recipeService.getAllCategories()).thenReturn(new ArrayList<>());

    assertThrows(
        CategoryNotFoundException.class,
        () -> {
          recipeController.getAllCategories();
        });
  }
}
