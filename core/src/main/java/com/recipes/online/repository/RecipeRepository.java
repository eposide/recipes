package com.recipes.online.repository;

import com.recipes.online.model.RecipeModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository  extends JpaRepository<RecipeModel, Long> {

    List<RecipeModel> findAllByCategorizedFalse();
}
