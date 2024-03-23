/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cw12;

/**
 *
 * @author USER
 */

//create  a subclass named Clothes and inherit the super class named Product....
public class Clothes extends Product{
    private String productSize;
    private String productColour;
    
    //create a constructor to identify a cloth item.............................
    public Clothes(String productId, String productName, int productNumberOfAvailableItems, double productPrice, String productSize, String productColour) {
        super(productId, productName, productNumberOfAvailableItems, productPrice);
        this.productSize = productSize;
        this.productColour = productColour;
    }
    //create a getter for get the ProductSize
    public String getProductSize() {
        return productSize;
    }
    //create a setter for set the ProductSize
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    //create a getter for get the ProductColour
    public String getProductColour() {
        return productColour;
    }
    //create a setter for set the ProductColour
    public void setProductColour(String productColour) {
        this.productColour = productColour;
    }
}
