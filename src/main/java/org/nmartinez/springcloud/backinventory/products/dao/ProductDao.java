package org.nmartinez.springcloud.backinventory.products.dao;

import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<ProductEntity, Long> {
}
