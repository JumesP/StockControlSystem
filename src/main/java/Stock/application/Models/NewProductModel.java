package Stock.application.Models;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;
import Stock.classes.Misc.Clock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class NewProductModel {
    Connection connection;

    private File image;

    public NewProductModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);

        this.image = null;
    }

    public File getImage() {
        return this.image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void Add(String Product_Name, Integer Product_Quantity, Integer Product_Price) {
        String query; PreparedStatement PreparedStatement;

        Clock clock = new Clock(); clock.date();

        try {
            query = "INSERT INTO products (Product_ID, Product_Name, Product_Quantity, Product_Price, Last_Stocked) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement = connection.prepareStatement(query);
            PreparedStatement.setString(1, String.valueOf(generateProductID()));
            PreparedStatement.setString(2, Product_Name);
            PreparedStatement.setString(3, String.valueOf(Product_Quantity));
            PreparedStatement.setString(4, String.valueOf(Product_Price));
            PreparedStatement.setInt(5, clock.sortableDate());
            PreparedStatement.executeUpdate();
            PreparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int generateProductID() {
        String query;
        Statement statement;
        ResultSet resultSet;

        try {
            query = "SELECT COUNT(*) FROM products"; statement = connection.createStatement(); resultSet = statement.executeQuery(query);
            System.out.println(resultSet);
            System.out.println(resultSet.getInt(1));

            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
