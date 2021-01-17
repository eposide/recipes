package com.recipes.online.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @NotBlank(message = "Name is mandatory")
    @JsonProperty("name")
    private String name;
    @NotBlank(message = "Quantity is mandatory")
    @NotNull(message = "Quantity is mandatory")
    @JsonProperty("quantity")
    private String quantity;

}
