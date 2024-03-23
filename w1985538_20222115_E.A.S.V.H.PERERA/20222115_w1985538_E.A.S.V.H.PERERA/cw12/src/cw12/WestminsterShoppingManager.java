

package cw12;


import static cw12.ConsoleApplication.input;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.*;
//import java.util.Iterator;

/**
 *
 * @author USER
 */
public class WestminsterShoppingManager implements Serializable,ShoppingManager {

    private static Scanner input = new Scanner(System.in);

   //create arrays for project....................................................

    public ArrayList<Product> productList = new ArrayList<>();
    public ArrayList<User> userList = new ArrayList<>();
    
   

    //the process of adding a product..............................................
    @Override
    public void addProduct(Product product) {
        if (productList.size() != 50) {

            //checking product id is unique
            if (checkProductIdIsUnique(product.getProductId())) {
                productList.add(product);
                System.out.println("Id - " + product.getProductId() + " name-" + product.getProductName() + " item added !");
            }else {
                System.out.println("Given product Id is already exists !");
            }
        } else {
            System.out.println("Maximum items 50 limit is reached.");
        }
    }
    
    //check the uniqueness of product id.............................................
    private boolean checkProductIdIsUnique(String newProductId) {
        Boolean isProductIdUnique = true;

        for (Product product : productList) {
            //if (newProductId.equalsIgnoreCase(product.getProductId())) {
            if(product.getProductId().equals(newProductId) ){
                isProductIdUnique = false;
                break;
            }
        }
        return isProductIdUnique;
    }

     //display the menu................................................................
    public void displayMenu(){
     System.out.println("THE MENU");
            System.out.println("Enter 1 for Add a product");
            System.out.println("Enter 2 for Delete a product");
            System.out.println("Enter 3 for print the list of the products");
            System.out.println("Enter 4 for save in a file");
            System.out.println("Enter 5 load data from a file");
            System.out.println("Enter 6 open the gui");
    }
    //delete a electronic item........................................................
    @Override
    public void deleteProduct(String productId) {
        boolean isProductFound = false;

        for (Product product : productList) {
            if (productId.equalsIgnoreCase(product.getProductId())) {
                isProductFound = true;
                System.out.println("Successfully removed Item !");
                printProduct(product);
                productList.remove(product);
                System.out.println(productList.size());
                break;
            }
        }

        if (!isProductFound) {
            System.out.println("Given product id not exists !");
        }
    }

    //print the list of the products..................................................
    @Override
    public void printProductList() {
        if (productList.size() == 0){
            System.out.println("Empty product list !");
        }

        //sorting base on name using alphabetic order
        Collections.sort(productList);

        for (Product product : productList) {
            printProduct(product);
        }
        System.out.println();
    }

    //check the type of product..........................................................
    private void printProduct(Product product) {
        System.out.println("----------------------------------");
        if (product instanceof Clothes) {
            printClothProduct((Clothes) product);
        } else {
            printElectronicProduct((Electronic) product);
        }
        System.out.println("----------------------------------");
    }

    //print the electronic products
    private void printElectronicProduct(Electronic electronicProduct) {
        System.out.println("Product type : Electronic");
        System.out.println("Product ID : " + electronicProduct.getProductId());
        System.out.println("Product name : " + electronicProduct.getProductName());
        System.out.println("Product price : " + electronicProduct.getProductPrice());
        System.out.println("Product brand : " + electronicProduct.getProductBrand());
        System.out.println("Product warranty period : " + electronicProduct.getProductWarrantyPeriod());
        System.out.println("Product  number available items: " + electronicProduct.getProductNumberOfAvailableItems());
    }
   

    //print the cloth products
    private void printClothProduct(Clothes clothProduct) {
        System.out.println("Product type : Cloth");
        System.out.println("Product ID : " + clothProduct.getProductId());
        System.out.println("Product name : " + clothProduct.getProductName());
        System.out.println("Product price : " + clothProduct.getProductPrice());
        System.out.println("Product size : " + clothProduct.getProductSize());
        System.out.println("Product colour : " + clothProduct.getProductColour());
        System.out.println("Product  number available items: " + clothProduct.getProductNumberOfAvailableItems());
    }

    //save the products in a file
    @Override
    public void saveInFile() {

     //create a file
     File f1 = new File("file1.txt"); //create a file
    try{
        //create a FileOutputStream towrite data to the file
        FileOutputStream fout=new FileOutputStream(f1);
        //Create an ObjectOutputStream to write objects
        ObjectOutputStream objout=new ObjectOutputStream(fout);
                //Iterate the productList
                Iterator it = productList.iterator();
                while (it.hasNext()){
                
                Product saveProduct=(Product) it.next();
                //write the Product objects to ObjectOutputStream
                objout.writeObject(saveProduct);

                }
                System.out.println("file saved successfully");
          }catch(Exception e){}

        }

    //load data from the file.............................................................
    @Override
    public void loadDataFromFile() {
          //clear the productlist 
          productList.clear();
            try {
                FileInputStream fin = new FileInputStream("file1.txt");
                ObjectInputStream obj = new ObjectInputStream(fin);

             while (true) {
                    try {
                       Product e = (Product) obj.readObject();
                        productList.add(e);
                   } catch (Exception e) {
                        break;
                    }
                }
            } catch (Exception e) {
                
            }
            //print the product list 
            System.out.println(productList);

    }
    //create a method to open gui
    @Override
    public void openGUI() {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.println("Enter your username:");
            String username = input.next();

            int isExcistingUser = authenticateUser(username);

            if (isExcistingUser != 2) {
                System.out.println("Login successful!");
                Myframe frame = new Myframe();
                frame.setTitle("Shopping Cart");
                frame.setSize(1000, 650);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Pass the productList to the updateTable method
                frame.updateTable(productList, isExcistingUser);

                frame.setVisible(true);
                return; // Exit the method after successful login
            } else {
                System.out.println("Invalid username or password. Try again.");
                attempts++;
            }
        }

        System.out.println("Maximum login attempts reached. Exiting.");
    }


    private int authenticateUser(String username) {
        loadUsersFromFile();
        int isExcistingUser = 0;
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                // User with the given username found
                isExcistingUser = 1;
                // Ask for the password
                System.out.println("Enter your password:");
                String enteredPassword = input.next();

                // Check if the entered password is correct
                if (enteredPassword.equals(user.getPassword())) {
                    return isExcistingUser; // User authenticated
                } else {
                    System.out.println("Incorrect password. Try again.");
                    return 2; // Incorrect password
                }
            }
        }

        // If user is not found, create a new user and save to userList
        System.out.println("User not found. Creating a new user.");

        // Ask for the new password
        System.out.println("Enter a new password:");
        String newPassword = input.next();

        User newUser = new User(username, newPassword);
        userList.add(newUser);
        saveUsersToFile(); // Save the updated userList to file

        return isExcistingUser;
    }

    //save users to the file...............................................................
    private void saveUsersToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("users.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            for (User user : userList) {
                objectOutputStream.writeObject(user);
            }

            System.out.println("Users saved to file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //load the users from the file.........................................................
    private void loadUsersFromFile() {
        userList.clear();

        try (FileInputStream fileInputStream = new FileInputStream("users.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            while (true) {
                try {
                    User user = (User) objectInputStream.readObject();
                    userList.add(user);
                } catch (Exception e) {
                    break;
                }
            }

            System.out.println("Users loaded from file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void enterUserDetails(){}

    
    
}

