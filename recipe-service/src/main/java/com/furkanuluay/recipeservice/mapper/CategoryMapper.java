package com.furkanuluay.recipeservice.mapper;

import com.furkanuluay.recipeservice.entity.Category;
import com.furkanuluay.recipeservice.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Furkan Uluay
 */
@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(CategoryMapper.class);

  Category dtoToEntity(CategoryDto dto);

  CategoryDto entityToDto(Category entity);
}
