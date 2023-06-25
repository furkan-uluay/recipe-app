package com.furkanuluay.recipeservice.repository;

import com.furkanuluay.recipeservice.domain.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Furkan Uluay
 */

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  List<Recipe> findByCategoriesContainingAndTitleContaining(String category, String title);

  List<Recipe> findByCategoriesContaining(String category);

  List<Recipe> findByTitleContaining(String title);

  boolean existsByTitle(String title);

  @Query("SELECT DISTINCT r.categories FROM Recipe r")
  List<String> findAllCategories();
}
