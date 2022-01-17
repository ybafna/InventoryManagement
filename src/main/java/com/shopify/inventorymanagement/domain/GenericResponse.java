package com.shopify.inventorymanagement.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenericResponse<T> {

    private T data;
    private String error;

    public GenericResponse(T data, String error) {
        this.data = data;
        this.error = error;
    }
}
