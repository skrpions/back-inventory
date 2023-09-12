package org.nmartinez.springcloud.backinventory.domain.dao;

import org.nmartinez.springcloud.backinventory.domain.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<CategoryEntity, Long> {
}
