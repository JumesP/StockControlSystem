package Stock.classes.Product_Departments.Grocery_Departments.Ambient_Department;

import Stock.classes.Product_Departments.Grocery_Departments.Ambient;

public class Chocolate extends Ambient {

    private static String Description = "The finest chocolate in the store!";
    private String Department = "Chocolate";
    private int Department_ID = 9;
    private int Storage_Temperature = 20;

    public Chocolate(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, int Department_ID){
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
