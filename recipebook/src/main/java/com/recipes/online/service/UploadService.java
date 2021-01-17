package com.recipes.online.service;

import com.recipes.online.dao.Recipe;
import com.recipes.online.dao.UploadResponse;
import com.recipes.online.model.RecipeConverter;
import com.recipes.online.model.RecipeModel;
import com.recipes.online.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UploadService {

    @Autowired
    RecipeRepository recipeRepository;

    private static final String UPLOAD_RESPONSE_MESSAGE =
            "Congratulations! Your recipe has been uploaded and will soon be available to all your fans";

    /**
     * Create a uncategorized recipe in the recipe book
     * @param recipe
     * @return an UploadResponse which includes a reference number
     */
    public UploadResponse createRecipe(Recipe recipe) {

        log.info("Creating recipe " + new RecipeConverter().toJson(recipe));
        RecipeModel savedModel = recipeRepository.save(new RecipeModel(new RecipeConverter().toJson(recipe), false));

        return UploadResponse.builder()
                .referenceNumber(savedModel.getId())
                .message(UPLOAD_RESPONSE_MESSAGE).build();
    }

}
