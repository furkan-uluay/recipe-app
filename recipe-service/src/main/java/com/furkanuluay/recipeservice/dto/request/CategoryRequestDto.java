package com.furkanuluay.recipeservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotEmpty(message = "Category Name is required")
    private String name;
}
