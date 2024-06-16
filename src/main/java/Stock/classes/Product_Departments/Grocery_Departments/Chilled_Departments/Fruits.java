package Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments;

import Stock.classes.Product_Departments.Grocery_Departments.Chilled;

public class Fruits extends Chilled{
    private String Department = "Fruit";

    public Fruits(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, String Useby_Date){
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Useby_Date);
        this.Department = Department;
    }
}