/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cw12;

/**
 *
 * @author USER
 */
//create a interface named Shoppingmanager....................................
public interface ShoppingManager {
    
    //ceate a method for add product
    void addProduct(Product product);
    
    //ceate a method for delete product
    void deleteProduct(String productId);
    
    //ceate a method for print the product list
    void printProductList();
    
    //ceate a method for save products in a file
    void saveInFile();
    
    //ceate a method for load data from filr
    void loadDataFromFile();
    
    //ceate a method for open GUI
    void openGUI();
}
