package com.furkanuluay.recipeservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Furkan Uluay
 */
@Entity
@Table(name = "recipe")
@Getter
@Setter
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
        name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;

  private String yield;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Ingredient> ingredients;

  @Lob private String directions;

  public List<Ingredient> getIngredients() {
    if (ingredients == null) {
      return new ArrayList<>();
    }
    return ingredients;
  }
}
