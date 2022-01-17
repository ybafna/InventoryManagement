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
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long productId;
    private String productName;
    private String productDescription;
    private String urlToImage;
    private int quantity;
    private double price;

    public Product(String productName, String productDescription, String urlToImage, int quantity, double price){
        this.productDescription = productDescription;
        this.productName = productName;
        this.urlToImage = urlToImage;
        this.quantity = quantity;
        this.price = price;
    }

}
