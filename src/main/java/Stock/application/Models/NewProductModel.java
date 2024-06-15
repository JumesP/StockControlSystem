package Stock.application.Models;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;
import Stock.classes.Misc.Clock;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewProductModel {
    Connection connection;

    public NewProductModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public void Add(String Product_Name, Integer Product_Quantity, Integer Product_Price, String Last_Stocked) {
        String query;
        PreparedStatement PreparedStatement;
        Statement statement;
        ResultSet resultSet;

        Clock clock = new Clock();

        try {
            //Product ID
            query = "SELECT COUNT(*) FROM products";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Integer Product_ID = resultSet.getInt(1);
            resultSet.close();

            clock.date();

            query = "INSERT INTO products (Product_ID, Product_Name, Product_Quantity, Product_Price, Last_Stocked) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement = connection.prepareStatement(query);
            PreparedStatement.setString(1, String.valueOf(Product_ID));
            PreparedStatement.setString(2, Product_Name);
            PreparedStatement.setString(3, String.valueOf(Product_Quantity));
            PreparedStatement.setString(4, String.valueOf(Product_Price));
            PreparedStatement.setInt(5, clock.sortableDate());
            PreparedStatement.executeUpdate();
            PreparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
