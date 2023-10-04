package org.nmartinez.springcloud.backinventory.categories.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.categories.responses.CategoryResponseRest;
import org.nmartinez.springcloud.backinventory.categories.services.CategoryService;
import org.nmartinez.springcloud.backinventory.shared.utils.CategoryExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"}) // CORS, solo recibiré peticiones de este servidor
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

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> add(@RequestBody CategoryEntity category) {
        ResponseEntity<CategoryResponseRest> response = categorySrv.add(category);

        return response;
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@PathVariable Long id, @RequestBody CategoryEntity category) {
        ResponseEntity<CategoryResponseRest> response = categorySrv.update(id, category);

        return response;
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categorySrv.delete(id);

        return response;
    }

    @GetMapping ("/categories/export/excel")
            public void exportToExcel (HttpServletResponse response) throws IOException {
        response.setContentType ("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_category.xlsx";
        response. setHeader (headerKey, headerValue);
        ResponseEntity<CategoryResponseRest> categoryResponse = categorySrv.list();
        CategoryExcelExporter excelExporter = new CategoryExcelExporter (
                categoryResponse.getBody().getCategoryResponse().getCategory());

        excelExporter.export(response);
    }
}
