package org.nmartinez.springcloud.backinventory.products.services;

import org.nmartinez.springcloud.backinventory.categories.dao.CategoryDao;
import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.products.dao.ProductDao;
import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;
import org.nmartinez.springcloud.backinventory.products.responses.ProductResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;


    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> add(ProductEntity product, Long categoryId) {
        ProductResponseRest response = new ProductResponseRest();
        List<ProductEntity> list = new ArrayList<>();

        try {

            //Search category to set in the product object
            Optional<CategoryEntity> category = categoryDao.findById(categoryId);

            if (category.isPresent()){
                product.setCategory(category.get()); // Add idCategory en Product Object
            } else {
                response.setMetadata("nOk", "-1", "Categoría no encontrada!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // Save the product
            ProductEntity productAdded = productDao.save(product);

            if (productAdded != null){
                list.add(productAdded);
                response.getProductResponse()
                        .setProduct(list);
                response.setMetadata("Ok", "200", "Producto registrado!");
            } else{
                response.setMetadata("nOk", "-1", "Producto no registrado!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Falló al intentar registrar!");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }
}
