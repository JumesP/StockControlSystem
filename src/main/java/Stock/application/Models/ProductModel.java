package Stock.application.Models;
import Stock.application.SqliteConnection;
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
        String query = "SELECT * FROM products ORDER BY Product_ID ASC";
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
            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // database doesnt work so return empty list to load page
        }
//        return null;
    }

    public List<All_Products> FetchSearchedData(String search) {
        String query;
        PreparedStatement PreparedStatement;
        Statement statement;
        ResultSet resultSet;

        System.out.println();

        try {
            if (search.equals("")) {
                query = "SELECT * FROM products ORDER BY Product_ID ASC";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
            } else {
                query = "SELECT * FROM products WHERE Product_Name LIKE ? ORDER BY Product_ID ASC";
                PreparedStatement = connection.prepareStatement(query);
                PreparedStatement.setString(1, "%" + search + "%");
                resultSet = PreparedStatement.executeQuery();
//                PreparedStatement.close();
            }
            List<All_Products> searchResults = new ArrayList<>();
            while (resultSet.next()) {
                searchResults.add(new All_Products(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Price"), resultSet.getInt("Product_Quantity"), resultSet.getString("Last_Stocked")));
            }
            resultSet.close();
            return searchResults;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
