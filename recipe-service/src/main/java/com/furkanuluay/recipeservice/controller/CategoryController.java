package com.furkanuluay.recipeservice.controller;

import com.furkanuluay.recipeservice.dto.CategoryDto;
import com.furkanuluay.recipeservice.service.CategoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;
  @GetMapping()
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }
}
