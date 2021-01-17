package com.recipes.online.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RECIPE_META")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeMetaDataModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "meta_key")
    private String metaKey;

    @Column(name = "meta_value")
    private String metaValue;

    public RecipeMetaDataModel(Long recipeId,  String metaKey, String metaValue) {
        this.recipeId = recipeId;
        this.metaKey = metaKey;
        this.metaValue = metaValue;
    }

}
