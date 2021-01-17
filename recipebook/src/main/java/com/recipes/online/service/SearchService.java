package com.recipes.online.service;

import com.recipes.online.dao.Recipe;
import com.recipes.online.dao.SearchResponse;
import com.recipes.online.model.MetaDataKeys;
import com.recipes.online.model.RecipeConverter;
import com.recipes.online.model.RecipeMetaDataModel;
import com.recipes.online.model.RecipeModel;
import com.recipes.online.repository.RecipeMetaDataRepository;
import com.recipes.online.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchService {

    @Autowired
    RecipeMetaDataRepository recipeMetaDataRepository;

    @Autowired
    RecipeRepository recipeRepository;

    public SearchResponse searchByIngredient(String searchTerm) {

        log.info("Searching for ingredients like : {}", searchTerm );
        List<RecipeMetaDataModel> recipeMetaDataModelList =
                recipeMetaDataRepository.findAllByMetaKeyAndMetaValueLike(MetaDataKeys.INGREDIENT.getName(), searchTerm);


        return processResult(recipeMetaDataModelList);
    }

    public SearchResponse searchByType(String searchTerm) {

        log.info("Searching for recipe types : {}", searchTerm );
        List<RecipeMetaDataModel> recipeMetaDataModelList =
                recipeMetaDataRepository.findAllByMetaKeyAndMetaValueLike(MetaDataKeys.RECIPE_TYPE.getName(), searchTerm);

        return processResult(recipeMetaDataModelList);
    }

    private SearchResponse processResult(List<RecipeMetaDataModel> recipeMetaDataModelList) {

        List<Recipe> recipeList = new ArrayList<>();

        recipeMetaDataModelList.forEach(recipeMetaDataModel ->
                findRecipe(recipeMetaDataModel.getRecipeId())
                        .ifPresent(recipeModel ->
                                recipeList.add(new RecipeConverter().toRecipe(recipeModel.getRecipe()))));

        return SearchResponse.builder()
                .recipeList(recipeList)
                .build();


    }

    private Optional<RecipeModel> findRecipe(Long id)
    {
        return recipeRepository.findById(id);
    }


}
