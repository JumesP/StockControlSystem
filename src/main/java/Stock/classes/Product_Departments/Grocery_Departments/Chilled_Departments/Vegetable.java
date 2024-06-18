package Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments;

import Stock.classes.Product_Departments.Grocery_Departments.Chilled;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Vegetable extends Chilled {
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    private String Department = "Vegetable";
    private int Department_ID = 1;
    private String Description = "Freshly picked from the farm!";

    public Vegetable(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked){
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

    // METHODS
//    public void addVegetable(){
//        // Add a new vegetable to the database
//        super.addProduct();
//    }
}
