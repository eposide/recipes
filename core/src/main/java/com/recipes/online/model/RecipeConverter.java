package com.recipes.online.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipes.online.dao.Recipe;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecipeConverter  {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String toJson(Recipe recipe) {

        String recipeJson = null;
        try {
            recipeJson = objectMapper.writeValueAsString(recipe);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return recipeJson;
    }

    public Recipe toRecipe(String recipeJson) {

        Recipe recipe = null;
        try {
            recipe = objectMapper.readValue(recipeJson, Recipe.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return recipe;
    }

}
