package com.furkanuluay.recipeservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Furkan Uluay
 */

@Entity
@Table(name = "ingredient")
@Getter
@Setter
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String amount;

  private String unit;

  private String item;
}
