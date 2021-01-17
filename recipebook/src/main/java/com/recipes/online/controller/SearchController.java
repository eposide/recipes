package com.recipes.online.controller;

import com.recipes.online.dao.SearchResponse;
import com.recipes.online.dao.UploadResponse;
import com.recipes.online.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipebook/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/ingredient/{searchTerm}")
    public ResponseEntity<SearchResponse> searchByIngredient(@PathVariable("searchTerm") String searchTerm) {
        return new ResponseEntity<>(searchService.searchByIngredient(searchTerm), HttpStatus.OK);
    }
    @GetMapping("/type/{searchTerm}")
    public ResponseEntity<SearchResponse> searchByType(@PathVariable("searchTerm") String searchTerm) {
         return new ResponseEntity<>(searchService.searchByType(searchTerm), HttpStatus.OK);
    }

}
