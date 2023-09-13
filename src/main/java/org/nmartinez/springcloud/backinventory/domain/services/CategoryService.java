package org.nmartinez.springcloud.backinventory.domain.services;

import org.nmartinez.springcloud.backinventory.domain.responses.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    public ResponseEntity<CategoryResponseRest> list();
    public ResponseEntity<CategoryResponseRest> listOne(Long id);
}
