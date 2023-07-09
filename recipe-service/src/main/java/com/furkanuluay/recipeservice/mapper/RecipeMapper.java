package com.furkanuluay.recipeservice.mapper;

import com.furkanuluay.recipeservice.entity.Recipe;
import com.furkanuluay.recipeservice.dto.RecipeDto;
import com.furkanuluay.recipeservice.dto.request.RecipeRequestDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Furkan Uluay
 */
@Component
@Mapper(componentModel = "spring")
public interface RecipeMapper {

  RecipeMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(RecipeMapper.class);

  Recipe dtoToEntity(RecipeRequestDto dto);
  RecipeDto entityToDto(Recipe entity);
  
}
