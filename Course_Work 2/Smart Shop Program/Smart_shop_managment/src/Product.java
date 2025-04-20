import javax.swing.*;

public class Product {
    String product;
    int quantity;


    Product(){ //constructor default for Product, there will always be 1.
        quantity = 1;
        product = "Coke";
    }
    public String getProduct() { //Getter
        return product;
    }
    public void setProduct(String product) { //Setter
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
