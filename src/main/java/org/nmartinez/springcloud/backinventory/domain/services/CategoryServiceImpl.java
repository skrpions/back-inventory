package org.nmartinez.springcloud.backinventory.domain.services;

import org.nmartinez.springcloud.backinventory.domain.dao.CategoryDao;
import org.nmartinez.springcloud.backinventory.domain.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.domain.responses.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired private CategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> list() {

        CategoryResponseRest response = new CategoryResponseRest();

        try {
            List<CategoryEntity> category = (List<CategoryEntity>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(category);
            response.setMetadata("Ok", "00", "Respuesta Exitosa!");
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Falló la consulta!");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> listOne(Long id) {

        CategoryResponseRest response = new CategoryResponseRest();
        List<CategoryEntity> list = new ArrayList<>();

        try {
            Optional<CategoryEntity> category = categoryDao.findById(id);

            if (category.isPresent()){
                list.add(category.get());
                response.getCategoryResponse()
                        .setCategory(list);
                response.setMetadata("Ok", "200", "Categoría encontrada!");
            } else{
                response.setMetadata("nOk", "-1", "Categoría no encontrada!");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Falló la consulta por Id!");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> add(CategoryEntity category) {

        CategoryResponseRest response = new CategoryResponseRest();
        List<CategoryEntity> list = new ArrayList<>();

        try {
            CategoryEntity categoryAdded = categoryDao.save(category);

            if (categoryAdded != null){
                list.add(categoryAdded);
                response.getCategoryResponse()
                        .setCategory(list);
                response.setMetadata("Ok", "200", "Categoría registrada!");
            } else{
                response.setMetadata("nOk", "-1", "Categoría no registrada!");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            response.setMetadata("nOk", "-1", "Falló al intentar registrar!");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

    }

}
