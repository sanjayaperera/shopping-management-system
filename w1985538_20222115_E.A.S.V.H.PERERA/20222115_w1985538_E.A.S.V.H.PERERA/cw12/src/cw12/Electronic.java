/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cw12;

//import java.io.Serializable;
//import java.util.Iterator;

/**
   private String brand;
 *
 * @author USER
 */
//create  a subclass named Electronic and inherit the super class Product.......
public class Electronic extends Product {
    
    private String productBrand;
    private String productWarrantyPeriod;
    
    //create a constructor to identify a Electronic item........................
    public Electronic(String productId, String productName, int productNumberOfAvailableItems, double productPrice, String productBrand, String productWarrantyPeriod) {
        super(productId, productName, productNumberOfAvailableItems, productPrice);
        this.productBrand = productBrand;
        this.productWarrantyPeriod = productWarrantyPeriod;
    }
    //create a getter for get the ProductBrand
    public String getProductBrand() {
        return productBrand;
    }
    //create a setter for set the ProductBrand
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }
    //create a getter for get the warrentyperiod
    public String getProductWarrantyPeriod() {
        return productWarrantyPeriod;
    }
    //create a setter for set the warrenty period
    public void setProductWarrantyPeriod(String productWarrantyPeriod) {
        this.productWarrantyPeriod = productWarrantyPeriod;
    }
}
