package com.furkanuluay.recipeservice.service;

import com.furkanuluay.recipeservice.entity.Category;
import com.furkanuluay.recipeservice.dto.CategoryDto;
import com.furkanuluay.recipeservice.exception.CategoryNotFoundException;
import com.furkanuluay.recipeservice.mapper.CategoryMapper;
import com.furkanuluay.recipeservice.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryDto> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    if (categories.isEmpty()) {
      throw new CategoryNotFoundException();
    }

    return categories.stream()
        .map(CategoryMapper.INSTANCE::entityToDto)
        .collect(Collectors.toList());
  }

  public List<Category> findByNameContainingIgnoreCase(String category) {
    List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(category);
    if (categories == null || categories.isEmpty()) {
      throw new CategoryNotFoundException();
    }
    return categories;
  }
}
