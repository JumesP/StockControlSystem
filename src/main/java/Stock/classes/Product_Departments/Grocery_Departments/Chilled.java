package Stock.classes.Product_Departments.Grocery_Departments;

import Stock.classes.Product_Departments.Grocery;

public class Chilled extends Grocery {
    private Number Storage_Temperature = 4;

    public Chilled(String Product_Name, Number Product_ID, Number Product_Price, Number Product_Quantity, String Last_Stocked, String Useby_Date, Number Storage_Temperature) {
        super(Product_Name, Product_ID, Product_Price, Product_Quantity, Last_Stocked, Useby_Date);
        this.Storage_Temperature = Storage_Temperature;
    }
}
