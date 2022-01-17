package com.shopify.inventorymanagement.services;

import com.shopify.inventorymanagement.exceptions.CustomException;
import com.shopify.inventorymanagement.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product updateProduct(Product product, long productId) throws CustomException;

    void deleteProduct(long productId) throws CustomException;

    Product addProduct(Product product);
}
