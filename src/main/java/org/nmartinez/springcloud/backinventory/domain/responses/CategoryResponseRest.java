package org.nmartinez.springcloud.backinventory.domain.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseRest extends ResponseRest {

    private CategoryResponse categoryResponse = new CategoryResponse();
}
