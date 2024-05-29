package Stock.classes.Product_Departments;

import Stock.classes.All_Products;

public class Grocery extends All_Products {

    private String Useby_Date;

//    public Grocery(String Product_Name, Number Product_ID, Number Product_Price, Number Product_Quantity, String Last_Stocked) {
//        super(Product_Name, Product_ID, Product_Price, Product_Quantity, Last_Stocked);
//    }

    public Grocery(String Product_Name, Number Product_ID, Number Product_Price, Number Product_Quantity, String Last_Stocked, String Useby_Date) {
        super(Product_Name, Product_ID, Product_Price, Product_Quantity, Last_Stocked);
        this.Useby_Date = Useby_Date;
    }
}
