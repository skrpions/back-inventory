package org.nmartinez.springcloud.backinventory.domain.responses;

import lombok.Data;
import org.nmartinez.springcloud.backinventory.domain.entities.CategoryEntity;

import java.util.List;

@Data
public class CategoryResponse {

    private List<CategoryEntity> category;
}
