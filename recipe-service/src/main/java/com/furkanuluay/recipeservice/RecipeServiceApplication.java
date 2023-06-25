package com.furkanuluay.recipeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Furkan Uluay
 */

@EntityScan("com.furkanuluay.recipeservice.domain")
@EnableJpaRepositories("com.furkanuluay.recipeservice.repository")
@EnableTransactionManagement
@SpringBootApplication
public class RecipeServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(RecipeServiceApplication.class, args);
  }
}
