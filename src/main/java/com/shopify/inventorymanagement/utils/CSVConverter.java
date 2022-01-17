package com.shopify.inventorymanagement.utils;

import com.shopify.inventorymanagement.exceptions.CustomException;
import com.shopify.inventorymanagement.models.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CSVConverter {

    public static ByteArrayInputStream tutorialsToCSV(List<Product> productList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            List<String> header = Arrays.asList("Product Id",
                    "Product Name",
                    "Product Description",
                    "Image Url",
                    "Quantity",
                    "Price");

            csvPrinter.printRecord(header);
            for (Product product : productList) {
                List<String> data = Arrays.asList(
                        String.valueOf(product.getProductId()),
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getUrlToImage(),
                        String.valueOf(product.getQuantity()),
                        String.valueOf(product.getPrice()));

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new CustomException("Failed to import data to CSV file: " + e.getMessage());
        }
    }
}
