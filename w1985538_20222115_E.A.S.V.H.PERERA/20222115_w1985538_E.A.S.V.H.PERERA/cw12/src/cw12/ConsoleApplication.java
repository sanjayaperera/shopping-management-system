package cw12;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleApplication {
    
    //create a scanner to get inputs.............................................................
    public static Scanner input = new Scanner(System.in);
    private static WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

    public static void main(String args[]) throws IOException {

        while(true){
            //display the menu
            displayMenu();
            //input the command user want to do
            String menu=input.next();
            switch(menu){
                case "1":
                    addProductToSystem();
                    break;
                case "2":
                    deleteProductFromSystem();
                    break;
                case "3":
                    printSystemProducts();
                    break;
                case "4":
                    shoppingManager.saveInFile();
                    break;
                case "5":
                    shoppingManager.loadDataFromFile();
                    break;
                case "6":
                    shoppingManager.openGUI();
                    // Handle GUI functionality
                    break;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");

            }
        }
    }
    //display the menu.......................................................... 
    private static void displayMenu() {
        shoppingManager.displayMenu();
    }
    //print the products in the system..........................................
    private static void printSystemProducts() {
        System.out.println("System product details: ");
        shoppingManager.printProductList();
    }
    //delete a product in the system...........................................
    private static void deleteProductFromSystem() {
        System.out.println("enter product Id if you want to delete :");
        String delProductId=input.next();
        shoppingManager.deleteProduct(delProductId);
    }
    //add product...............................................................
    private static void addProductToSystem() {
        System.out.println("enter a for add electronic item and enter b for add cloth");
        //get the user input about product categary
        String item=input.next();

        Product newProduct = null;

        //add a electronic product
        if(item.equalsIgnoreCase("a")) {
            //add the product id 
            System.out.println("enter the electronic item product id");
            String ProductId = input.next();
            while(!ProductId.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid product id!");
            ProductId= input.next(); 
            }  
            
            //add the product name
            System.out.println("enter the electronic item product name");
            String productName = input.next();
            while(!productName.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid product name!");
            productName= input.next(); 
            }  
            
            //add the product brand
            System.out.println("enter the electronic item brand");
            String electronicItemBand = input.next();
            while(!electronicItemBand.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid electronic item brand!");
            electronicItemBand= input.next(); 
            }  
            
            //add the warrenty period
            System.out.println("enter the  electronic item warranty period");
            String electronicItemWarrantPeriod = input.next();
            while(!electronicItemWarrantPeriod.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid warrenty period!");
            electronicItemWarrantPeriod= input.next(); 
            }  
            
            //get the count of adding product
            System.out.println("enter the electronic item num of available items");
            int electronicItemAvailableItems = input.nextInt();
            
            //add the  price of th product
            System.out.println("enter the electronic item price");
            double productPrice = input.nextDouble();
            
            //create a new electronic object
            newProduct = new Electronic(ProductId, productName, electronicItemAvailableItems, productPrice, electronicItemBand, electronicItemWarrantPeriod);
            
            //add a cloth product
        } else if (item.equalsIgnoreCase("b")) {
            //add the product id
            System.out.println("enter the cloth item product id");
            String ProductId = input.next();
            while(!ProductId.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid product id!");
            ProductId= input.next(); 
            }
            
             //add the product name
            System.out.println("enter the cloth item product name");
            String productName = input.next();
            while(!productName.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid product name!");
            productName= input.next(); 
            }
            
            //add the cloth item size
            System.out.println("enter the cloth item size");
            String clothItemSize= input.next();
            while(!clothItemSize.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid cloth item size!");
            clothItemSize= input.next(); 
            }
            
            //add the cloth item colour
            System.out.println("enter the  cloth item colour");
            String clothItemColour = input.next();
            while(!clothItemColour.matches("[0-9a-zA-Z]+")){
             System.out.println("Please enter a valid clothItem colour!");
            clothItemColour= input.next(); 
            }
            
            //add the count of adding product
            System.out.println("enter the cloth item num of available items");
            int clothItemAvailableItems = input.nextInt();
            
            
            //add the product price
            System.out.println("enter the cloth item price");
            double productPrice = input.nextDouble();
            
            //create a new cloth object
            newProduct = new Clothes(ProductId, productName, clothItemAvailableItems, productPrice, clothItemSize, clothItemColour);

        }else {
            System.out.println("Invalid option given !");
            return;
        }
        
        //add the product as a new product to the system........................ 
        shoppingManager.addProduct(newProduct);
    }
}
