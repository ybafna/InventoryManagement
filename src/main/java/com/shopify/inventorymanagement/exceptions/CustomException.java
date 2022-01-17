package com.shopify.inventorymanagement.exceptions;

public class CustomException extends RuntimeException{

    private static final long serialVersionUID = -7839760861044487811L;

    /**
     * Function to throw custom exceptions
     * @param message Error Message
     */
    public CustomException(String message){
        super(message);
    }
}
