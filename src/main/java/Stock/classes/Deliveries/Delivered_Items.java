package Stock.classes.Deliveries;

public class Delivered_Items {
    private Integer Product_ID;
    private Integer Product_Quantity;

    public Delivered_Items(Integer Product_ID, Integer Product_Quantity) {
        this.Product_ID = Product_ID;
        this.Product_Quantity = Product_Quantity;
    }

    public Integer getProduct_ID() {
        return Product_ID;
    }

    public Integer getProduct_Quantity() {
        return Product_Quantity;
    }

    public void setProduct_ID(Integer Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setProduct_Quantity(Integer Product_Quantity) {
        this.Product_Quantity = Product_Quantity;
    }
}
