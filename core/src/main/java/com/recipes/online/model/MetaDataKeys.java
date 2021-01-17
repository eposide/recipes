package com.recipes.online.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum MetaDataKeys {

    //Known metadata keys
    CHEF_NAME("chef_name"),
    TITLE("recipe_title"),
    PORTIONS("portions"),
    TIME_TO_PREPARE("time_to_prepare"),
    RECIPE_TYPE("recipe_type"),
    INGREDIENT("ingredient");

    private final String name;

}