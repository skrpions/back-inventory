package org.nmartinez.springcloud.backinventory.products.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.nmartinez.springcloud.backinventory.categories.responses.CategoryResponseRest;
import org.nmartinez.springcloud.backinventory.products.entities.ProductEntity;
import org.nmartinez.springcloud.backinventory.products.responses.ProductResponseRest;
import org.nmartinez.springcloud.backinventory.products.services.ProductService;
import org.nmartinez.springcloud.backinventory.shared.utils.CategoryExcelExporter;
import org.nmartinez.springcloud.backinventory.shared.utils.ProductExcelExporter;
import org.nmartinez.springcloud.backinventory.shared.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"}) // CORS, solo recibiré peticiones de este servidor
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

    @Autowired private ProductService productSrv;

    @GetMapping("/products")
    public ResponseEntity<ProductResponseRest> list() {
        ResponseEntity<ProductResponseRest> response = productSrv.list();

        return response;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> listProductById(@PathVariable Long id) {
        ResponseEntity<ProductResponseRest> response = productSrv.listOne(id);

        return response;
    }

    @GetMapping("/products/filter/{name}")
    public ResponseEntity<ProductResponseRest> listProductByName(@PathVariable String name) {
        ResponseEntity<ProductResponseRest> response = productSrv.listByName(name);

        return response;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> add(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("account") int account,
            @RequestParam("categoryId") Long categoryID) throws IOException {

        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        product.setAccount(account);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productSrv.add(product, categoryID);

        return response;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> update(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("account") int account,
            @RequestParam("categoryId") Long categoryID,
            @PathVariable Long id) throws IOException {

        try {
            ProductEntity product = new ProductEntity();
            product.setName(name);
            product.setPrice(price);
            product.setAccount(account);
            product.setPicture(Util.compressZLib(picture.getBytes()));

            ResponseEntity<ProductResponseRest> response = productSrv.update(id, product, categoryID);

            return response;
        }
        catch(Exception e) {
            // Manejar la excepción
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> delete(@PathVariable Long id) {
        ResponseEntity<ProductResponseRest> response = productSrv.delete(id);

        return response;
    }

    @GetMapping ("/products/export/excel")
    public void exportToExcel (HttpServletResponse response) throws IOException {
        response.setContentType ("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_products.xlsx";
        response. setHeader (headerKey, headerValue);
        ResponseEntity<ProductResponseRest> productResponse = productSrv.list();
        ProductExcelExporter excelExporter = new ProductExcelExporter (
                productResponse.getBody().getProductResponse().getProduct());

        excelExporter.export(response);
    }
}
