package cw12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//create a class named ShoppingCart.............................................
public class ShoppingCart {
    private Map<Product, Integer> cartItems;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
    }
    
    // create a method to add a product to the shopping cart
    public void addToCart(Product product) {
        // Check if the product is already in the cart
        if (cartItems.containsKey(product)) {
            
            int currentQuantity = cartItems.get(product);
            cartItems.put(product, currentQuantity + 1);
        } else {
            cartItems.put(product, 1);
        }
    }
    // Create a method to retrieve the map of items in the shopping cart along with their quantities
    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }
}
