package com.shopify.inventorymanagement.services;


import com.shopify.inventorymanagement.exceptions.CustomException;
import com.shopify.inventorymanagement.models.Product;
import com.shopify.inventorymanagement.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Function to retrieve all the products in the inventory
     * @return List of Products in the inventory
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Function to update the product details
     * @param newProduct Product with new product details
     * @param productId Id of the product
     * @return Product with updated details
     * @throws CustomException in case of invalid productId
     */
    @Override
    public Product updateProduct(Product newProduct, long productId) throws CustomException{
        if(productRepository.existsById(productId)){
            Product product = productRepository.getById(productId);
            updateProductDetails(product, newProduct);
            productRepository.save(product);
            return product;
        } else {
            throw new CustomException("No product found with productId " + newProduct.getProductId());
        }
    }

    /**
     * Updates the existing product with the new values
     * @param product Existing product in the inventory
     * @param newProduct Product with new details
     */
    private void updateProductDetails(Product product, Product newProduct) {
        if(newProduct.getProductName() != null && !newProduct.getProductName().equals(""))
            product.setProductName(newProduct.getProductName());

        if(newProduct.getProductDescription() != null && !newProduct.getProductDescription().equals(""))
            product.setProductDescription(newProduct.getProductDescription());

        if(newProduct.getUrlToImage() != null && !newProduct.getUrlToImage().equals(""))
            product.setUrlToImage(newProduct.getUrlToImage());

        if(newProduct.getPrice() > 0)
            product.setPrice(newProduct.getPrice());

        if(newProduct.isOutOfStock()) {
            product.setOutOfStock(true);
            product.setQuantity(0);
        } else {
            if(newProduct.getQuantity() > 0) {
                product.setQuantity(newProduct.getQuantity());
                product.setOutOfStock(false);
            }
        }
    }

    /**
     * Function to delete an existing product
     * @param productId Id of the product
     * @throws CustomException in case of invalid productId
     */
    @Override
    public void deleteProduct(long productId) throws CustomException{
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
        } else {
            throw new CustomException("No product found with productId " + productId);
        }
    }

    /**
     * Function to add a new product to the inventory
     * @param product Product to be added
     * @return Newly added product
     */
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
