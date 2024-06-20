package Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments;

import Stock.application.SqliteConnection;
import Stock.classes.Misc.Clock;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static Stock.application.SqliteConnection.connection;
import static Stock.classes.Misc.Clock.getSortableDateInAWeek;

public class Fruits extends Chilled{
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    Clock clock = new Clock();

    static {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

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


    public void addFruit() {
        // Add a new fruit product to the database
        query = "insert into products (Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID, Useby_Date, Storage_Temp) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.getProduct_Name());
            preparedStatement.setInt(2, generateID());
            preparedStatement.setInt(3, super.getProduct_Restock_Price());
            preparedStatement.setInt(4, super.getProduct_Sale_Price());
            preparedStatement.setInt(5, super.getProduct_Quantity());
            preparedStatement.setInt(6, clock.sortableDate());
            preparedStatement.setInt(7, this.Department_ID);
            preparedStatement.setInt(8, clock.sortableDate());
            preparedStatement.setInt(9, super.getStorage_Temperature());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}