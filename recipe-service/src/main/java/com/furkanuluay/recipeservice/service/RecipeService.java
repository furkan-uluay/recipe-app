package com.furkanuluay.recipeservice.service;

import com.furkanuluay.recipeservice.domain.Recipe;
import com.furkanuluay.recipeservice.exception.RecipeNotFoundException;
import com.furkanuluay.recipeservice.repository.RecipeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Furkan Uluay
 */
@Service
public class RecipeService {

  @Autowired private RecipeRepository recipeRepository;

  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  public List<Recipe> findByCategoryAndSearch(String category, String search) {
    return recipeRepository.findByCategoriesContainingAndTitleContaining(category, search);
  }

  public List<Recipe> findByCategory(String category) {
    return recipeRepository.findByCategoriesContaining(category);
  }

  public List<Recipe> findBySearch(String search) {
    return recipeRepository.findByTitleContaining(search);
  }

  public boolean isRecipeNameExists(String name) {
    return recipeRepository.existsByTitle(name);
  }

  public Recipe createRecipe(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  public List<String> getAllCategories() {
    return recipeRepository.findAllCategories();
  }

  public Recipe getRecipeById(Long id) {
    return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
  }
}
