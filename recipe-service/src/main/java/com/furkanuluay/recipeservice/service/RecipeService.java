package com.furkanuluay.recipeservice.service;

import com.furkanuluay.recipeservice.entity.Category;
import com.furkanuluay.recipeservice.entity.Recipe;
import com.furkanuluay.recipeservice.exception.RecipeAlreadyExistException;
import com.furkanuluay.recipeservice.exception.RecipeNotFoundException;
import com.furkanuluay.recipeservice.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Furkan Uluay
 */
@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepository;
  private final CategoryService categoryService;

  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  public List<Recipe> findByCategory(String category) {

    List<Category> categories = categoryService.findByNameContainingIgnoreCase(category);
    return categories.stream()
        .map(Category::getRecipe)
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  public List<Recipe> findByTitle(String title) {
    return recipeRepository.findByTitleContainingIgnoreCase(title);
  }

  public boolean isRecipeNameExists(String name) {
    return recipeRepository.existsByTitle(name);
  }

  @Transactional
  public Recipe createRecipe(Recipe recipe) {
    if (isRecipeNameExists(recipe.getTitle())) {
      throw new RecipeAlreadyExistException();
    }
    return recipeRepository.save(recipe);
  }

  public Recipe getRecipeById(Long id) {
    Recipe recipe = recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    return recipe;
  }
}
