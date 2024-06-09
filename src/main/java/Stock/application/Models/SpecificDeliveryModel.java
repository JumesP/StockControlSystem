package Stock.application.Models;

import Stock.application.SqliteConnection;
import Stock.classes.Deliveries.Ordered_Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecificDeliveryModel {
    Connection connection;

    public SpecificDeliveryModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public List<Ordered_Items> FetchData(Integer deliveryId) {
        String query = "SELECT * FROM Orders WHERE Delivery_ID = ? ORDER BY Product_Quantity ASC";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, deliveryId);
            resultSet = preparedStatement.executeQuery();
            List<Ordered_Items> data = new ArrayList<>();
            while (resultSet.next()) {
                System.out.println(resultSet);
                System.out.println(resultSet.getString("Product_Name"));
                System.out.println(resultSet.getInt("Product_ID"));
                data.add(new Ordered_Items(resultSet.getInt("Product_ID"), resultSet.getString("Product_Name"), resultSet.getInt("Product_Quantity")));
            }
            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // database doesnt work so return empty list to load page
        }
//        return null;
    }
}
