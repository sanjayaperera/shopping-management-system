/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cw12;

import java.io.Serializable;

/**
 *
 * @author USER
 */
//create a abstract class named product
public abstract class Product implements Serializable, Comparable {
    
    private String productId;
    private String productName;
    private int productNumberOfAvailableItems;
    private double productPrice;

    public Product(String productId, String productName, int productNumberOfAvailableItems, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productNumberOfAvailableItems = productNumberOfAvailableItems;
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNumberOfAvailableItems() {
        return productNumberOfAvailableItems;
    }

    public void setProductNumberOfAvailableItems(int productNumberOfAvailableItems) {
        this.productNumberOfAvailableItems = productNumberOfAvailableItems;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public int compareTo(Object o) {
        Product product = (Product) o;

        if (this.getProductName().compareToIgnoreCase(product.getProductName()) > 0){
            return 1;
        } else if (this.getProductName().compareToIgnoreCase(product.getProductName()) < 0) {
            return -1;
        }else {
            return 0;
        }
    }

    Object getProductColour() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getProductBrand() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
