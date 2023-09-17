package org.nmartinez.springcloud.backinventory.categories.responses;

import lombok.Getter;
import lombok.Setter;
import org.nmartinez.springcloud.backinventory.shared.responses.ResponseRest;

@Getter
@Setter
public class CategoryResponseRest extends ResponseRest {

    private CategoryResponse categoryResponse = new CategoryResponse();
}
