package com.furkanuluay.recipeservice.mapper;

import com.furkanuluay.recipeservice.domain.Recipe;
import com.furkanuluay.recipeservice.dto.RecipeRequestDto;
import com.furkanuluay.recipeservice.dto.RecipeResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Furkan Uluay
 */
@Component
@Mapper(componentModel = "spring")
public interface RecipeMapper {
  Recipe dtoToEntity(RecipeRequestDto dto);
  RecipeResponseDto entityToDto(Recipe entity);
  
}
