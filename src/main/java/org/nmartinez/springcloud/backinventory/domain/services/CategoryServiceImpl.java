package org.nmartinez.springcloud.backinventory.domain.services;

import org.nmartinez.springcloud.backinventory.domain.dao.CategoryDao;
import org.nmartinez.springcloud.backinventory.domain.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.domain.responses.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired private CategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

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
}
