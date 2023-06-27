package com.furkanuluay.recipeservice.controller;

import com.furkanuluay.recipeservice.domain.Recipe;
import com.furkanuluay.recipeservice.dto.CategoryDto;
import com.furkanuluay.recipeservice.dto.RecipeRequestDto;
import com.furkanuluay.recipeservice.dto.RecipeResponseDto;
import com.furkanuluay.recipeservice.exception.CategoryNotFoundException;
import com.furkanuluay.recipeservice.exception.RecipeAlreadyExistException;
import com.furkanuluay.recipeservice.exception.RecipeNotFoundException;
import com.furkanuluay.recipeservice.mapper.RecipeMapper;
import com.furkanuluay.recipeservice.service.RecipeService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Furkan Uluay
 *     <p>RecipeController is the controller class for Recipe entity. It has 4 endpoints. 1. Get all
 *     recipes 2. Search recipes by category and search text by title 3. Create recipe 4. Get all
 *     categories
 */
@RestController
@RequestMapping("/recipes")
public class RecipeController {

  private final RecipeService recipeService;
  private final RecipeMapper recipeMapper;

  public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
    this.recipeService = recipeService;
    this.recipeMapper = recipeMapper;
  }

  @GetMapping
  public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() {
    List<Recipe> recipes = recipeService.getAllRecipes();

    List<RecipeResponseDto> recipeResponseDtosList =
        recipes.stream().map(recipeMapper::entityToDto).collect(Collectors.toList());

    if (recipeResponseDtosList.isEmpty()) {
      throw new RecipeNotFoundException();
    }

    return ResponseEntity.ok(recipeResponseDtosList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long id) {
    Recipe recipe = recipeService.getRecipeById(id);
    return ResponseEntity.ok(recipeMapper.entityToDto(recipe));
  }

  @GetMapping("/search")
  public ResponseEntity<List<RecipeResponseDto>> searchRecipes(
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String search) {
    List<Recipe> recipes;

    if (category != null && search != null) {
      recipes = recipeService.findByCategoryAndSearch(category, search);
    } else if (category != null) {
      recipes = recipeService.findByCategory(category);
    } else if (search != null) {
      recipes = recipeService.findBySearch(search);
    } else {
      return ResponseEntity.badRequest().build();
    }
    if (recipes == null || recipes.isEmpty()) {
      throw new RecipeNotFoundException();
    }

    List<RecipeResponseDto> recipeResponseDtosList =
        recipes.stream().map(recipeMapper::entityToDto).collect(Collectors.toList());
    return ResponseEntity.ok(recipeResponseDtosList);
  }

  @PostMapping
  public ResponseEntity<RecipeResponseDto> createRecipe(
      @Valid @RequestBody RecipeRequestDto recipe) {
    if (recipeService.isRecipeNameExists(recipe.getTitle())) {
      throw new RecipeAlreadyExistException();
    }
    Recipe createdRecipe = recipeService.createRecipe(recipeMapper.dtoToEntity(recipe));
    return ResponseEntity.status(HttpStatus.CREATED).body(recipeMapper.entityToDto(createdRecipe));
  }

  @GetMapping("/categories")
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    List<String> categories = recipeService.getAllCategories();
    if (categories == null || categories.isEmpty()) {
      throw new CategoryNotFoundException();
    }

    List<CategoryDto> categoryDtoList =
        categories.stream().map(CategoryDto::new).collect(Collectors.toList());

    return ResponseEntity.ok(categoryDtoList);
  }
}
