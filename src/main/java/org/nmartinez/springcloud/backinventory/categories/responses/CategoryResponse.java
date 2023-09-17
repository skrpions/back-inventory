package org.nmartinez.springcloud.backinventory.categories.responses;

import lombok.Data;
import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;

import java.util.List;

@Data
public class CategoryResponse {

    private List<CategoryEntity> category;
}
