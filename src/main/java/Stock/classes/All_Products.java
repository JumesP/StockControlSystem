package Stock.classes;

public class All_Products {
    private final String Product_Name;
    private final Number Product_ID;
    private final Number Product_Price;
    private final Number Product_Quantity;
    private final String Last_Stocked;

    public All_Products(String Product_Name, Number Product_ID, Number Product_Price, Number Product_Quantity, String Last_Stocked) {
        this.Product_Name = Product_Name;
        this.Product_ID = Product_ID;
        this.Product_Price = Product_Price;
        this.Product_Quantity = Product_Quantity;
        this.Last_Stocked = Last_Stocked;
    }

//    public All_Products() {
//        Product_Name = "Hello";
//    }



    public String Product_Name() {
        return Product_Name;
    }

    public Number Product_ID() {
        return Product_ID;
    }

    public Number Product_Price() {
        return Product_Price;
    }

    public Number Product_Quantity() {
        return Product_Quantity;
    }

    public String Last_Stocked() {
        return Last_Stocked;
    }
}
