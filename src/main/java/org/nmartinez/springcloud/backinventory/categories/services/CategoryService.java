package org.nmartinez.springcloud.backinventory.categories.services;

import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.categories.responses.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    public ResponseEntity<CategoryResponseRest> list();
    public ResponseEntity<CategoryResponseRest> listOne(Long id);
    public ResponseEntity<CategoryResponseRest> add(CategoryEntity category);
    public ResponseEntity<CategoryResponseRest> update(Long id, CategoryEntity category);
    public ResponseEntity<CategoryResponseRest> delete(Long id);
}
