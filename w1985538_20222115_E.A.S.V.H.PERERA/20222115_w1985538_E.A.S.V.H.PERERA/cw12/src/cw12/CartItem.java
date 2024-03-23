package cw12;

//create a class named CartItem.................................................
public class CartItem {
    private Product product;
    private int quantity;

    //create the conustructor...................................................
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    //add getter to get product
    public Product getProduct() {
        return product;
    }

    //add getter to get quantity
    public int getQuantity() {
        return quantity;
    }
    //add getter to get total price 
    public double getTotalPrice() {
        return product.getProductPrice() * quantity;
    }
}
