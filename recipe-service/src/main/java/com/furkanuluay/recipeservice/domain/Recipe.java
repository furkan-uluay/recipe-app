package com.furkanuluay.recipeservice.domain;

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

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> categories;

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
