public class Product {
   public String product;
   public int quantity;


    Product(){ //constructor default for Product, there will always be 1.
        quantity = 1;
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
