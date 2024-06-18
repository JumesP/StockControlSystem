package Stock.classes.Deliveries;

import Stock.application.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ordered_Items {
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    public static void connection() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }


    private int Product_ID;
    private String Product_Name;
    private int Product_Quantity;

    public Ordered_Items (int Product_ID, String Product_Name, int Product_Quantity) {
        this.Product_ID = Product_ID;
        this.Product_Name = Product_Name;
        this.Product_Quantity = Product_Quantity;
    }


    public int getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public int getProduct_Quantity() {
        return Product_Quantity;
    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public void setProduct_Quantity(int Product_Quantity) {
        this.Product_Quantity = Product_Quantity;
    }


    // STATIC METHODS
    public static List<Ordered_Items> FetchOrderedItemsByDeliveryID(int ID) {
        query = "SELECT * FROM Orders WHERE Delivery_ID = " + ID;
        try {
            connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Ordered_Items> data = new ArrayList<>();

            while (resultSet.next()) {
                data.add(new Ordered_Items(resultSet.getInt("Product_ID"), resultSet.getString("Product_Name"), resultSet.getInt("Product_Quantity")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
