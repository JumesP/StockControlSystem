package Stock.application;
import Stock.classes.All_Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {

    Connection connection;

    public ProductModel() {
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

    public List<All_Products> FetchData() {
        String query = "SELECT * FROM products";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<All_Products> data = new ArrayList<>();
            while (resultSet.next()) {
                System.out.println(resultSet);
                System.out.println(resultSet.getString("Product_Name"));
                System.out.println(resultSet.getInt("Product_ID"));
                data.add(new All_Products(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Price"), resultSet.getInt("Product_Quantity"), resultSet.getString("Last_Stocked")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
