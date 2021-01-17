package com.recipes.online.component;

import com.recipes.online.service.CategorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RecipeCategorizationSchedule {

    @Autowired
    private CategorizationService categorizationService;

    @Scheduled(fixedDelay = 15000, initialDelay = 15000)
    public void findUncategorizedRecipes() {
        categorizationService.categorizeRecipes();
    }
}
