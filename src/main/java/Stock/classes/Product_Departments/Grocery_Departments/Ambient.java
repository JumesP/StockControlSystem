package Stock.classes.Product_Departments.Grocery_Departments;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;
import Stock.classes.Misc.Clock;
import Stock.classes.Product_Departments.Grocery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static Stock.application.SqliteConnection.Select;
import static Stock.application.SqliteConnection.connection;

public class Ambient extends Grocery {
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

    String sugar_content = "a lot";
    int Storage_Temperature = 20;

    public Ambient(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, int Department_ID) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID);
        this.sugar_content = sugar_content;
        this.Storage_Temperature = Storage_Temperature;
    }

    public String getSugar_Content() {
        return sugar_content;
    }

    public int getStorage_Temperature() {
        return Storage_Temperature;
    }

    public void addAmbient(String department) {
        // Add a new fruit product to the database
        query = "insert into products (Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID, Useby_Date, Storage_Temp) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        int Department_ID;
        switch (department) {
            case "Crisps":
                Department_ID = 7;
                break;
            case "Sweets":
                Department_ID = 8;
                break;
            case "Chocolate":
                Department_ID = 9;
                break;
            default:
                Department_ID = -1;
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

    public static Ambient getAmbientByID(int id) {
        query = "SELECT * FROM Products WHERE Product_ID = " + id;
        try (ResultSet results = Select(query)) {
            if (results.next()) {
                return new Ambient(results.getString("Product_Name"), results.getInt("Product_ID"), results.getInt("Product_Restock_Price"), results.getInt("Product_Sale_Price"), results.getInt("Product_Quantity"), results.getInt("Last_Stocked"), results.getInt("Department_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
