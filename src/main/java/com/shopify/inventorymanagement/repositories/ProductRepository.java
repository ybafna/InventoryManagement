package com.shopify.inventorymanagement.repositories;

import com.shopify.inventorymanagement.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
