package org.nmartinez.springcloud.backinventory.products.dao;

import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<ProductEntity, Long> {

    // Personalized Querie: Find Product by Name
    //@Query("Select p From Product p Where p.name Like %?1%")
    //List<ProductEntity> findByNameLike(String name);

    // Personalized Method: Find Product by Name : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<ProductEntity> findByNameContainingIgnoreCase(String name);


}
