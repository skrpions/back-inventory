package org.nmartinez.springcloud.backinventory.domain.controllers;

import org.nmartinez.springcloud.backinventory.domain.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.domain.responses.CategoryResponseRest;
import org.nmartinez.springcloud.backinventory.domain.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * save categories
     * @param category
     * @return
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> add(@RequestBody CategoryEntity category) {
        ResponseEntity<CategoryResponseRest> response = categorySrv.add(category);

        return response;
    }

    /**
     * update cateories
     * @param id
     * @param category
     * @return
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@PathVariable Long id, @RequestBody CategoryEntity category) {
        ResponseEntity<CategoryResponseRest> response = categorySrv.update(id, category);

        return response;
    }
}
