package com.furkanuluay.recipeservice.controller;

import com.furkanuluay.recipeservice.entity.Recipe;
import com.furkanuluay.recipeservice.dto.RecipeDto;
import com.furkanuluay.recipeservice.dto.request.RecipeRequestDto;
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
 *     <p>RecipeController is the controller class for Recipe entity. It has 3 endpoints. 1. Get all
 *     recipes 2. Search recipes by category and search text by title 3. Create recipe
 */
@RestController
@RequestMapping("/recipes")
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping
  public ResponseEntity<List<RecipeDto>> getAllRecipes() {

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            recipeService.getAllRecipes().stream()
                .map(RecipeMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
    Recipe recipe = recipeService.getRecipeById(id);
    return ResponseEntity.status(HttpStatus.OK).body(RecipeMapper.INSTANCE.entityToDto(recipe));
  }

  @GetMapping("/search")
  public ResponseEntity<List<RecipeDto>> searchRecipes(
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String title) {
    List<Recipe> recipes;

    if (category != null) {
      recipes = recipeService.findByCategory(category);
    } else if (title != null) {
      recipes = recipeService.findByTitle(title);
    } else {
      return ResponseEntity.badRequest().build();
    }

    List<RecipeDto> recipeDtosList =
        recipes.stream().map(RecipeMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(recipeDtosList);
  }

  @PostMapping
  public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeRequestDto recipe) {

    Recipe createdRecipe = recipeService.createRecipe(RecipeMapper.INSTANCE.dtoToEntity(recipe));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(RecipeMapper.INSTANCE.entityToDto(createdRecipe));
  }
}
