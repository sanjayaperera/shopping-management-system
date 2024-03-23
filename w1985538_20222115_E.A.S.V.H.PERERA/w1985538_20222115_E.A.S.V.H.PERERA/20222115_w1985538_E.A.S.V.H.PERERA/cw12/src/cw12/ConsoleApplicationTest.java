package cw12;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class ConsoleApplicationTest {
    @Test
    public void testAddProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        Electronic electronicProduct = new Electronic("E001", "Laptop", 10, 999.99, "Dell", "1 year");
        shoppingManager.addProduct(electronicProduct);

        assertTrue(shoppingManager.productList.contains(electronicProduct));

        assertEquals(1, shoppingManager.productList.size());
    }

    @Test
    public void testDeleteProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        Electronic electronicProduct = new Electronic("E001", "Laptop", 10, 999.99, "Dell", "1 year");
        shoppingManager.addProduct(electronicProduct);

        shoppingManager.deleteProduct("E001");

        assertFalse(shoppingManager.productList.contains(electronicProduct));

        assertEquals(0, shoppingManager.productList.size());
    }

    @Test
    public void testPrintProductList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Electronic("E001", "Laptop", 10, 999.99, "Dell", "1 year"));
        productList.add(new Electronic("E002", "Smartphone", 8, 599.99, "Samsung", "2 years"));

        shoppingManager.productList = productList;

        shoppingManager.printProductList();

        String expectedOutput = "----------------------------------" + System.lineSeparator() +
                "Product type : Electronic" + System.lineSeparator() +
                "Product ID : E001" + System.lineSeparator() +
                "Product name : Laptop" + System.lineSeparator() +
                "Product price : 999.99" + System.lineSeparator() +
                "Product brand : Dell" + System.lineSeparator() +
                "Product warranty period : 1 year" + System.lineSeparator() +
                "Product  number available items: 10" + System.lineSeparator() +
                "----------------------------------" + System.lineSeparator() +
                "----------------------------------" + System.lineSeparator() +
                "Product type : Electronic" + System.lineSeparator() +
                "Product ID : E002" + System.lineSeparator() +
                "Product name : Smartphone" + System.lineSeparator() +
                "Product price : 599.99" + System.lineSeparator() +
                "Product brand : Samsung" + System.lineSeparator() +
                "Product warranty period : 2 years" + System.lineSeparator() +
                "Product  number available items: 8" + System.lineSeparator() +
                "----------------------------------" + System.lineSeparator() +
                System.lineSeparator();

        System.setOut(System.out);

        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    void testSaveAndLoadFile() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Electronic("E001", "Laptop", 10, 999.99, "Dell", "1 year"));
        productList.add(new Electronic("E002", "Smartphone", 8, 599.99, "Samsung", "2 years"));

        shoppingManager.productList = productList;

        File tempFile;
        try {
            tempFile = File.createTempFile("testProducts", ".dat");
        } catch (IOException e) {
            throw new RuntimeException("Error creating temporary file", e);
        }

        shoppingManager.saveInFile();

        shoppingManager.loadDataFromFile();

        assertEquals(productList, shoppingManager.productList);

        tempFile.delete();
    }





}