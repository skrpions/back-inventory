package org.nmartinez.springcloud.backinventory.products.services;


import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;
import org.nmartinez.springcloud.backinventory.products.responses.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    public ResponseEntity<ProductResponseRest> list();
    public ResponseEntity<ProductResponseRest> listOne(Long id);
    public ResponseEntity<ProductResponseRest> listByName(String name);
    public ResponseEntity<ProductResponseRest> add(ProductEntity product, Long categoryId);
    public ResponseEntity<ProductResponseRest> update(Long id, ProductEntity product, Long categoryId);
    public ResponseEntity<ProductResponseRest> delete(Long id);
}
