package Stock.classes.Product_Departments.Grocery_Departments.Frozen_Department;

import Stock.classes.Product_Departments.Grocery_Departments.Frozen;

public class Ice_Cream extends Frozen {

    static String Description = "The finest ice cream in the store!";
    String Department = "Ice Cream";
    int Department_ID = 5;
    int Storage_Temperature = -20;

    public Ice_Cream(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, int Department_ID){
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID);
        this.Description = Description;
        this.Department = Department;
        this.Department_ID = Department_ID;
        this.Storage_Temperature = Storage_Temperature;
    }

    public static String getDescription(){
        return Description;
    }

}
