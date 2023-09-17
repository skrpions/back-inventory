package org.nmartinez.springcloud.backinventory.products.services;


import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;
import org.nmartinez.springcloud.backinventory.products.responses.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    public ResponseEntity<ProductResponseRest> add(ProductEntity product, Long categoryId);
}
