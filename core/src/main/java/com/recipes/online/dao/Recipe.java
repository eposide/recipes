package com.recipes.online.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @JsonProperty("title")
    @NotBlank(message = "Title is mandatory")
    private String recipeTitle;
    @JsonProperty("type")
    @NotBlank(message = "Type is mandatory")
    private String recipeType;
    @JsonProperty("chefName")
    @NotBlank(message = "ChefName is mandatory")
    private String chefName;
    @Positive(message = "Portions is mandatory")
    @JsonProperty("portions")
    private int portions;
    @NotBlank(message = "TimeToPrepare is mandatory")
    @JsonProperty("timeToPrepare")
    private String timeToPrepare;
    @NotBlank(message = "TimeToCook is mandatory")
    @JsonProperty("timeToCook")
    private String timeToCook;
    @Valid
    @NotNull(message = "Ingredients are mandatory")
    @JsonProperty("ingredients")
    private List<Ingredient> ingredients;
    @NotNull(message = "Instructions are mandatory")
    @JsonProperty("instructions")
    private List<String> instructions;

}
