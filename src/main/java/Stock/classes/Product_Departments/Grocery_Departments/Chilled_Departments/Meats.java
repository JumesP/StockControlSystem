package Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments;

import Stock.classes.Product_Departments.Grocery_Departments.Chilled;

public class Meats extends Chilled {

    private String Department = "Meat";
    private int Department_ID = 2;
    private String Description = "Dont eat this raw! Very tasty when cooked!";

    public Meats(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked){
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked);
        this.Department = Department;
        this.Department_ID = Department_ID;
        this.Description = Description;
    }

    public String getDepartment(){
        return Department;
    }

    public int getDepartment_ID(){
        return Department_ID;
    }

    public String getDescription(){
        return Description;
    }
}
