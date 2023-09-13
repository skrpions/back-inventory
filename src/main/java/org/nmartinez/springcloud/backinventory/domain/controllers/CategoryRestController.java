package org.nmartinez.springcloud.backinventory.domain.controllers;

import org.nmartinez.springcloud.backinventory.domain.responses.CategoryResponseRest;
import org.nmartinez.springcloud.backinventory.domain.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired private CategoryService categorySrv;
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> listCategories() {
        ResponseEntity<CategoryResponseRest> response = categorySrv.list();

        return response;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> listCategoryById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categorySrv.listOne(id);

        return response;
    }
}
