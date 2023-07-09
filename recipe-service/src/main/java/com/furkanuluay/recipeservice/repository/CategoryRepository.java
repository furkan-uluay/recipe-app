package com.furkanuluay.recipeservice.repository;

import com.furkanuluay.recipeservice.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Furkan Uluay
*/

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContainingIgnoreCase(String name);

}
