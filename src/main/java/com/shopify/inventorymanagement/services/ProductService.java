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
            Product product = productRepository.getById(productId);
            updateProductDetails(product, newProduct);
            productRepository.save(product);
            return product;
        } else {
            throw new CustomException("No product found with productId " + newProduct.getProductId());
        }
    }

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
