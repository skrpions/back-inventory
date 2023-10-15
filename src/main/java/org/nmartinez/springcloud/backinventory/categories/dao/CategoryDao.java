package org.nmartinez.springcloud.backinventory.categories.dao;

import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<CategoryEntity, Long> {
}
