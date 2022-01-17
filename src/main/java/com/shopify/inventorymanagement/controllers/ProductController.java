package com.shopify.inventorymanagement.controllers;

import com.shopify.inventorymanagement.domain.GenericResponse;
import com.shopify.inventorymanagement.exceptions.CustomException;
import com.shopify.inventorymanagement.models.Product;
import com.shopify.inventorymanagement.services.ICSVService;
import com.shopify.inventorymanagement.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final IProductService productService;
    private final ICSVService csvService;

    public ProductController(IProductService productService, ICSVService csvService) {
        this.productService = productService;
        this.csvService = csvService;
    }

    /**
     * Function to retrieve list of all products in the inventory
     * @return List of Products in the inventory
     */
    @GetMapping("/products")
    private ResponseEntity<GenericResponse<List<Product>>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(new GenericResponse<>(productList, null));
    }

    /**
     * Function to update an existing product
     * @param product Product with new product details
     * @param productId Id of the product
     * @return Product with updated details or 404 Not Found if the productId is incorrect
     */
    @PutMapping("/product/{productId}")
    private ResponseEntity<GenericResponse<Product>> updateProduct(@RequestBody Product product, @PathVariable("productId") long productId){
        try {
            Product updatedProduct = productService.updateProduct(product, productId);
            return ResponseEntity.ok(new GenericResponse<>(updatedProduct, null));
        } catch (CustomException ce){
            LOGGER.debug(ce.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Function to add a new product
     * @param product Product to be added
     * @return Newly added product
     */
    @PostMapping("/product")
    private ResponseEntity<GenericResponse<Product>> addProduct(@RequestBody Product product){
        Product addedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body((new GenericResponse<>(addedProduct, null)));
    }

    /**
     * Function to delete an existing product
     * @param productId Id of the product
     * @return Nothing in case of successful delete or a 404 Not Found if productId is incorrect
     */
    @DeleteMapping("product/{productId}")
    private ResponseEntity<Object> deleteProduct(@PathVariable("productId") long productId){
        try {
            productService.deleteProduct(productId);
        } catch (CustomException ce){
            LOGGER.debug(ce.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.noContent().build();
    }


    /**
     * Function to get product information in a CSV file
     * @return CSV file containing all the products and its details
     */
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
            LOGGER.debug(ce.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }


}
