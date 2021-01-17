package com.recipes.online.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RECIPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Lob
   // @Convert(converter = RecipeConverter.class)
    @Column(name = "recipe")
    private String recipe;

    @Column(name = "categorized")
    private Boolean categorized;

    public RecipeModel(String recipe, boolean categorized) {
        this.recipe = recipe;
        this.categorized = categorized;
    }

}

