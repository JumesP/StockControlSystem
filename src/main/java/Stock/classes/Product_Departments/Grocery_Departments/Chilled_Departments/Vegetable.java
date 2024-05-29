package Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments;

import Stock.classes.Product_Departments.Grocery_Departments.Chilled;

public class Vegetable extends Chilled {
    private String Department = "Vegetable";

    public Vegetable(String Product_Name, Number Product_ID, Number Product_Price, Number Product_Quantity, String Last_Stocked, String Useby_Date, Number Storage_Temperature, String Department) {
        super(Product_Name, Product_ID, Product_Price, Product_Quantity, Last_Stocked, Useby_Date, Storage_Temperature);
        this.Department = Department;
    }
}
