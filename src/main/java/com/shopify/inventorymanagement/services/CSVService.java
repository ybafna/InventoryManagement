package com.shopify.inventorymanagement.services;

import com.shopify.inventorymanagement.exceptions.CustomException;
import com.shopify.inventorymanagement.models.Product;
import com.shopify.inventorymanagement.repositories.ProductRepository;
import com.shopify.inventorymanagement.utils.CSVConverter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class CSVService implements ICSVService{

    private final ProductRepository productRepository;

    public CSVService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Function to get a CSV file containing product information
     * @return A file stream containing product information
     * @throws CustomException in case of any IOException while creating file
     */
    @Override
    public ByteArrayInputStream getAllDataInCSV() throws CustomException{
        List<Product> productList = productRepository.findAll();
        ByteArrayInputStream in = CSVConverter.tutorialsToCSV(productList);
        return in;
    }
}
