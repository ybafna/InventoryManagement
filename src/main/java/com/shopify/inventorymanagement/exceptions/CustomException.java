package com.shopify.inventorymanagement.exceptions;

public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}
