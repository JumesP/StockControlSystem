package Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments;

import Stock.classes.Product_Departments.Grocery_Departments.Chilled;

public class Fruits extends Chilled{

    private String Department = "Fruit";
    private int Department_ID = 0;
    private String Description = "Freshest fruit in the store!";

    public Fruits(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked){
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