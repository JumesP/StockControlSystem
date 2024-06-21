package Stock.classes.Product_Departments.Grocery_Departments;

import Stock.application.SqliteConnection;
import Stock.classes.Misc.Clock;
import Stock.classes.Product_Departments.Grocery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static Stock.application.SqliteConnection.connection;

public class Chilled extends Grocery {
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

    private int Storage_Temperature = 4;

    public Chilled(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked);
        this.Storage_Temperature = Storage_Temperature;
    }

    public int getStorage_Temperature() {
        return Storage_Temperature;
    }


    // METHODS
//    public Boolean

    // STATIC METHODS
//    public static Boolean ifReorderable(int ID) {
//        // Chilled products can only be ordered once a week, so check if the product has been ordered in the last week
//
//    }

    public void addChilled(String department) {
        // Add a new fruit product to the database
        query = "insert into products (Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID, Useby_Date, Storage_Temp) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        int Department_ID;
        switch (department) {
            case "Fruit":
                Department_ID = 0;
                break;
            case "Vegetable":
                Department_ID = 1;
                break;
            case "Meat":
                Department_ID = 2;
                break;
            default:
                Department_ID = 0;
        }

        try {
            connection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.getProduct_Name());
            preparedStatement.setInt(2, generateID());
            preparedStatement.setInt(3, super.getProduct_Restock_Price());
            preparedStatement.setInt(4, super.getProduct_Sale_Price());
            preparedStatement.setInt(5, super.getProduct_Quantity());
            preparedStatement.setInt(6, clock.sortableDate());
            preparedStatement.setInt(7, Department_ID);
            preparedStatement.setInt(8, clock.sortableDate());
            preparedStatement.setInt(9, this.getStorage_Temperature());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
