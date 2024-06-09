package Stock.classes.Deliveries;

public class Ordered_Items {
    private int Product_ID;
    private String Product_Name;
    private int Product_Quantity;

    public Ordered_Items (int Product_ID, String Product_Name, int Product_Quantity) {
        this.Product_ID = Product_ID;
        this.Product_Name = Product_Name;
        this.Product_Quantity = Product_Quantity;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public int getProduct_Quantity() {
        return Product_Quantity;
    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public void setProduct_Quantity(int Product_Quantity) {
        this.Product_Quantity = Product_Quantity;
    }
}
