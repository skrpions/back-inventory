package org.nmartinez.springcloud.backinventory.products.responses;

import lombok.Getter;
import lombok.Setter;
import org.nmartinez.springcloud.backinventory.shared.responses.ResponseRest;

@Getter
@Setter
public class ProductResponseRest extends ResponseRest {

    private ProductResponse productResponse = new ProductResponse();
}
