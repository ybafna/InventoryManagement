package com.shopify.inventorymanagement.services;

import com.shopify.inventorymanagement.exceptions.CustomException;

import java.io.ByteArrayInputStream;

public interface ICSVService {

    ByteArrayInputStream getAllDataInCSV() throws CustomException;

}
