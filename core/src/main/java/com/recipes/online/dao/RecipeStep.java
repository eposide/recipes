package com.recipes.online.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeStep {

    @NotBlank(message = "Instruction is mandatory")
    @JsonProperty("instruction")
    private String instruction;

}
