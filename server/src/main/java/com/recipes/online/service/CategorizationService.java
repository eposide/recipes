package com.recipes.online.service;

import com.recipes.online.dao.Recipe;
import com.recipes.online.model.MetaDataKeys;
import com.recipes.online.model.RecipeConverter;
import com.recipes.online.model.RecipeMetaDataModel;
import com.recipes.online.model.RecipeModel;
import com.recipes.online.repository.RecipeMetaDataRepository;
import com.recipes.online.repository.RecipeRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategorizationService {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeMetaDataRepository recipeMetaDataRepository;
    public void categorizeRecipes() {

        List<RecipeModel> uncategorizedRecipes = recipeRepository.findAllByCategorizedFalse();

        if (!uncategorizedRecipes.isEmpty()) {
            log.info("Found {} recipes to categorize.", uncategorizedRecipes.size());
        }

        uncategorizedRecipes.forEach(recipeModel -> categorizeRecipe(recipeModel));

    }

    private void categorizeRecipe(RecipeModel recipeModel)
    {

        Recipe recipeToCategorize = new RecipeConverter().toRecipe(recipeModel.getRecipe());

        saveMetaData(recipeModel.getId(), MetaDataKeys.RECIPE_TYPE.getName(), recipeToCategorize.getRecipeType());
        saveMetaData(recipeModel.getId(), MetaDataKeys.CHEF_NAME.getName(), recipeToCategorize.getChefName());
        saveMetaData(recipeModel.getId(), MetaDataKeys.TIME_TO_PREPARE.getName(), recipeToCategorize.getTimeToPrepare());
        saveMetaData(recipeModel.getId(), MetaDataKeys.PORTIONS.getName(), String.valueOf(recipeToCategorize.getPortions()));
        saveMetaData(recipeModel.getId(), MetaDataKeys.TITLE.getName(), recipeToCategorize.getRecipeTitle());

        recipeToCategorize.getIngredients().forEach(ingredient -> saveMetaData(recipeModel.getId(),MetaDataKeys.INGREDIENT.getName(),ingredient.getName()));

        recipeModel.setCategorized(true);
        recipeRepository.save(recipeModel);

        log.info("Categorized recipe with id {} and is now available for search.", recipeModel.getId());

    }

    private void saveMetaData(Long recipeId, String metaDataKey, String metaDataValue) {
        recipeMetaDataRepository.save(new RecipeMetaDataModel(recipeId, metaDataKey, metaDataValue));
    }






}
