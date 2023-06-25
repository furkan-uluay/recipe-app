package com.furkanuluay.recipeservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Furkan Uluay
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private List<String> categories;
}