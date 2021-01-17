package com.recipes.online.repository;

import com.recipes.online.model.RecipeMetaDataModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeMetaDataRepository  extends JpaRepository<RecipeMetaDataModel, Long> {

    List<RecipeMetaDataModel> findAllByMetaKeyAndMetaValueLike(String metaKey, String metaValue);
}
