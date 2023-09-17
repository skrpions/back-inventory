package org.nmartinez.springcloud.backinventory.products.responses;

import lombok.Data;
import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;

import java.util.List;

@Data
public class ProductResponse {
    private List<ProductEntity> product;
}
