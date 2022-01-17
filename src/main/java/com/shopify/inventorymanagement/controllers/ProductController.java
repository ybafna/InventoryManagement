package com.shopify.inventorymanagement.controllers;

import com.shopify.inventorymanagement.domain.GenericResponse;
import com.shopify.inventorymanagement.exceptions.CustomException;
import com.shopify.inventorymanagement.models.Product;
import com.shopify.inventorymanagement.services.ICSVService;
import com.shopify.inventorymanagement.services.IProductService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final IProductService productService;
    private final ICSVService csvService;

    public ProductController(IProductService productService, ICSVService csvService) {
        this.productService = productService;
        this.csvService = csvService;
    }

    @GetMapping("/products")
    private ResponseEntity<GenericResponse<List<Product>>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(new GenericResponse<>(productList, null));
    }

    @PutMapping("/product/{productId}")
    private ResponseEntity<GenericResponse<Product>> updateProduct(@RequestBody Product product, @PathVariable("productId") long productId){
        try {
            Product updatedProduct = productService.updateProduct(product, productId);
            return ResponseEntity.ok(new GenericResponse<>(updatedProduct, null));
        } catch (CustomException ce){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/product")
    private ResponseEntity<GenericResponse<Product>> addProduct(@RequestBody Product product){
        Product addedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body((new GenericResponse<>(addedProduct, null)));
    }

    @DeleteMapping("product/{productId}")
    private ResponseEntity<Object> deleteProduct(@PathVariable("productId") long productId){
        try {
            productService.deleteProduct(productId);
        } catch (CustomException ce){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/download")
    public ResponseEntity<Resource> downloadCSV(){
        String filename = "products.csv";

        try {
            InputStreamResource file = new InputStreamResource(csvService.getAllDataInCSV());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(file);
        } catch(CustomException ce){
            return ResponseEntity.internalServerError().build();
        }

    }


}
