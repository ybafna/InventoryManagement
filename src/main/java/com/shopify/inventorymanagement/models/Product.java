package com.shopify.inventorymanagement.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = -8099472816720116324L;
    /**
     * Id of the Product
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long productId;

    /**
     * Name of the product
     */
    private String productName;

    /**
     * Description of the product
     */
    private String productDescription;

    /**
     * Url of the product image
     */
    private String urlToImage;

    /**
     * Product Quantity available
     */
    private int quantity;

    /**
     * Price of the product
     */
    private double price;

    /**
     * Is the product out of stock
     */
    private boolean outOfStock;

    /**
     * Constructor to create a new product
     * @param productName Name of the product
     * @param productDescription Description of the product
     * @param urlToImage Image url of the product
     * @param quantity Quantity available
     * @param price Price of the product
     * @param outOfStock Is the product out of stock
     */
    public Product(String productName, String productDescription, String urlToImage, int quantity, double price, boolean outOfStock){
        this.productDescription = productDescription;
        this.productName = productName;
        this.urlToImage = urlToImage;
        this.quantity = quantity;
        this.price = price;
        this.outOfStock = outOfStock;
    }

}
