package com.furkanuluay.recipeservice.repository;

import com.furkanuluay.recipeservice.entity.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Furkan Uluay
 */

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

  List<Recipe> findByTitleContainingIgnoreCase(String title);

  boolean existsByTitle(String title);

  @Query("SELECT DISTINCT r.categories FROM Recipe r")
  List<String> findAllCategories();
}
