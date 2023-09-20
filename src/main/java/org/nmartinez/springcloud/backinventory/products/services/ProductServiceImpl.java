package org.nmartinez.springcloud.backinventory.products.services;

import org.nmartinez.springcloud.backinventory.categories.dao.CategoryDao;
import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.products.dao.ProductDao;
import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;
import org.nmartinez.springcloud.backinventory.products.responses.ProductResponseRest;
import org.nmartinez.springcloud.backinventory.shared.utils.Util;
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

    @Autowired private ProductDao productDao;

    @Autowired private CategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> list() {
        ProductResponseRest response = new ProductResponseRest();
        List<ProductEntity> list = new ArrayList<>();
        List<ProductEntity> listAux = new ArrayList<>();

        try {
            // Search products
            listAux = (List<ProductEntity>) productDao.findAll();
            //Optional<ProductEntity> product = productDao.findByNameLike(name);

            if (listAux.size() > 0) {
                // Descompressed the images of all the products you find.
                listAux.stream().forEach((product) -> {
                    byte[] imageDescompressed = Util.decompressZLib(product.getPicture());
                    product.setPicture(imageDescompressed);

                    list.add(product);
                });
                response.getProductResponse()
                        .setProduct(list);
                response.setMetadata("Ok", "200", "Products Found!");
            } else{
                response.setMetadata("nOk", "-1", "Products Not Found!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Failed to search!");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> listOne(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<ProductEntity> list = new ArrayList<>();

        try {
            // Search product by id
            Optional<ProductEntity> product = productDao.findById(id);

            if (product.isPresent()) {

                // Descompressed Image Base 64
                byte[] imageDescompressed = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imageDescompressed);

                list.add(product.get());
                response.getProductResponse()
                        .setProduct(list);
                response.setMetadata("Ok", "200", "Product Found!");
            } else{
                response.setMetadata("nOk", "-1", "Product Not Found!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Query failed by Id!");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> listByName(String name) {
        ProductResponseRest response = new ProductResponseRest();
        List<ProductEntity> list = new ArrayList<>();
        List<ProductEntity> listAux = new ArrayList<>();

        try {
            // Search product by name
            listAux = productDao.findByNameContainingIgnoreCase(name);
            //Optional<ProductEntity> product = productDao.findByNameLike(name);

            if (listAux.size() > 0) {
                // Descompressed the images of all the products you find.
                listAux.stream().forEach((product) -> {
                    byte[] imageDescompressed = Util.decompressZLib(product.getPicture());
                    product.setPicture(imageDescompressed);

                    list.add(product);
                });
                response.getProductResponse()
                        .setProduct(list);
                response.setMetadata("Ok", "200", "Products Found!");
            } else{
                response.setMetadata("nOk", "-1", "Products Not Found!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Query failed by Name!");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

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
                response.setMetadata("nOk", "-1", "Category Not Found!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // Save the product
            ProductEntity productAdded = productDao.save(product);

            if (productAdded != null){
                list.add(productAdded);
                response.getProductResponse()
                        .setProduct(list);
                response.setMetadata("Ok", "200", "Registered Product!");
            } else{
                response.setMetadata("nOk", "-1", "Unregistered Product!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Failed to register!");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> update(Long id, ProductEntity product, Long categoryId) {
        ProductResponseRest response = new ProductResponseRest();
        List<ProductEntity> list = new ArrayList<>();

        try {
            // Search category by id
            Optional<CategoryEntity> category = categoryDao.findById(categoryId);

            if (category.isPresent()){
                // I will update the record
                product.setCategory(category.get());
            } else{
                response.setMetadata("nOk", "-1", "Product Not Found!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // Search product to update
            Optional<ProductEntity> productSearch = productDao.findById(id);
            if (productSearch.isPresent()) {

                //byte[] imageDescompressed = Util.decompressZLib(product.getPicture());

                // I will update the record
                productSearch.get().setAccont(product.getAccont());
                productSearch.get().setCategory(product.getCategory());
                productSearch.get().setName(product.getName());
                productSearch.get().setPicture(product.getPicture());
                productSearch.get().setPrice(product.getPrice());

                // Save the product in DB
                ProductEntity productoToUpdate = productDao.save(productSearch.get());

                if (productoToUpdate != null) {
                    list.add(productoToUpdate);
                    response.getProductResponse()
                            .setProduct(list);
                    response.setMetadata("Ok", "200", "Updated Product!");
                } else {
                    response.setMetadata("nOk", "-1", "Product Not Updated!");
                    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            } else{
                response.setMetadata("nOk", "-1", "Product Not Found!");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Failed to update!");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> delete(Long id) {
        ProductResponseRest response = new ProductResponseRest();

        try {
            productDao.deleteById(id);
            response.setMetadata("Ok", "00", "Deleted Product!");

        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Failed to delete!");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
}
