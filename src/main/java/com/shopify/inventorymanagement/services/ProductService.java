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

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product newProduct, long productId) throws CustomException{
        if(productRepository.existsById(productId)){
            Product product = productRepository.findById(productId).get();
            updateProductDetails(product, newProduct);
            productRepository.save(product);
            return product;
        } else {
            throw new CustomException("No product found with productId " + newProduct.getProductId());
        }
    }

    private void updateProductDetails(Product product, Product newProduct) {
        if(newProduct.getProductName() != null && newProduct.getProductName() != "")
            product.setProductName(newProduct.getProductName());

        if(newProduct.getProductDescription() != null && newProduct.getProductDescription() != "")
            product.setProductDescription(newProduct.getProductDescription());

        if(newProduct.getUrlToImage() != null && newProduct.getUrlToImage() != "")
            product.setUrlToImage(newProduct.getUrlToImage());

        product.setPrice(newProduct.getPrice());
        product.setQuantity(newProduct.getQuantity());
    }

    @Override
    public void deleteProduct(long productId) throws CustomException{
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
        } else {
            throw new CustomException("No product found with productId " + productId);
        }
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
