package Stock.classes;

public class All_Products {
    private String Product_Name;
    private Number Product_ID;
    private Number Product_Price;
    private Number Product_Quantity;
    private String Last_Stocked;

    public All_Products(String Product_Name, Number Product_ID, Number Product_Price, Number Product_Quantity, String Last_Stocked) {
        this.Product_Name = Product_Name;
        this.Product_ID = Product_ID;
        this.Product_Price = Product_Price;
        this.Product_Quantity = Product_Quantity;
        this.Last_Stocked = Last_Stocked;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public Number getProduct_ID() {
        return Product_ID;
    }

    public Number getProduct_Price() {
        return Product_Price;
    }

    public Number getProduct_Quantity() {
        return Product_Quantity;
    }

    public String getLast_Stocked() {
        return Last_Stocked;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public void setProduct_ID(Number Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setProduct_Price(Number Product_Price) {
        this.Product_Price = Product_Price;
    }

    public void setProduct_Quantity(Number Product_Quantity) {
        this.Product_Quantity = Product_Quantity;
    }

    public void setLast_Stocked(String Last_Stocked) {
        this.Last_Stocked = Last_Stocked;
    }
}
