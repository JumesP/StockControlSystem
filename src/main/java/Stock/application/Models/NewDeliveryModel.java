package Stock.application.Models;

import Stock.application.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewDeliveryModel {
    Connection connection;

    public NewDeliveryModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void Add(String Delivery_Name, String Delivery_Date, String Delivery_Company, ArrayList<ArrayList<String>> orderedProducts) {
        String query;
        PreparedStatement PreparedStatement;
        Statement statement;
        ResultSet resultSet;

        try {
            query = "SELECT COUNT(*) FROM Deliveries";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Integer Delivery_ID = resultSet.getInt(1);
            resultSet.close();

            query = "INSERT INTO Deliveries (Delivery_ID, Delivery_Name, Delivery_Date, Delivery_Company) VALUES (?, ?, ?, ?)";
            PreparedStatement = connection.prepareStatement(query);
            PreparedStatement.setString(1, String.valueOf(Delivery_ID));
            PreparedStatement.setString(2, Delivery_Name);
            PreparedStatement.setString(3, Delivery_Date);
            PreparedStatement.setString(4, Delivery_Company);
            PreparedStatement.executeUpdate();
            PreparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String> getProducts() {
        List<String> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(resultSet.getString("Product_Name"));
            }
            resultSet.close();
            statement.close();
//            connection.close();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void AddOrderedProducts(String Delivery_Name, String Delivery_Company, String Delivery_Date, ArrayList<ArrayList<String>> Ordered_Products) {
        String query;
        PreparedStatement PreparedStatement;
        Statement statement;
        ResultSet resultSet;

        System.out.println(Ordered_Products);

//        Ordered_Products = Ordered_Products.toArray();

        try {
            query = "SELECT COUNT(*) FROM Ordered_Products";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int Delivery_ID = resultSet.getInt(1);
            resultSet.close();

            query = "INSERT INTO Deliveries (Delivery_Name, Delivery_ID, Delivery_Company, Delivery_Date, Ordered_Products) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement = connection.prepareStatement(query);
            PreparedStatement.setString(1, Delivery_Name);
            PreparedStatement.setInt(2, Delivery_ID);
            PreparedStatement.setString(3, Delivery_Company);
            PreparedStatement.setString(4, Delivery_Date);
            PreparedStatement.setArray(5, (Array) Ordered_Products);
            PreparedStatement.executeUpdate();
            PreparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
