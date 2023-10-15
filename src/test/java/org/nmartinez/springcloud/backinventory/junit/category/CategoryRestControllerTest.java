package org.nmartinez.springcloud.backinventory.junit.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nmartinez.springcloud.backinventory.categories.controllers.CategoryRestController;
import org.nmartinez.springcloud.backinventory.categories.entities.CategoryEntity;
import org.nmartinez.springcloud.backinventory.categories.responses.CategoryResponseRest;
import org.nmartinez.springcloud.backinventory.categories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class CategoryRestControllerTest {

    static int count; // Conunt test
    // Testing service add
    @InjectMocks CategoryRestController categoryRestController;

    @Mock private CategoryService categorySrv; // Simula el comportamiento del servicio real y controla su comportamiento en el entorno de prueba.

    @BeforeEach // Se ejecuta al iniciar cada prueba unitaria
    public void init() {
        count++;
        System.out.println("* Test: " + count);

        MockitoAnnotations.openMocks(this);
    }

    // Start Unit Testing
    // --------------------------------------------------------
    @Test
    @DisplayName("Test Add Category")
    public void addTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Crear un objeto de prueba para CategoryEntity
        CategoryEntity category = new CategoryEntity();
        category.setId(Long.valueOf(1));
        category.setName("Lácteos");
        category.setDescription("Productos derivados de la leche");

        // Crear un objeto de prueba para ResponseEntity
        ResponseEntity<CategoryResponseRest> expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);

        // Configurar el comportamiento simulado del servicio CategoryService
        when(categorySrv.add(category)).thenReturn(expectedResponse);

        // Llamar al método del controlador que deseo testear
        ResponseEntity<CategoryResponseRest> actualResponse = categoryRestController.add(category);

        // Verificar que el resultado es el esperado
        assertThat(actualResponse).isEqualTo(expectedResponse);
        //assertThat(actualResponse.getStatusCodeValue()).isEqualTo(201); // Deprecated
    }



}
