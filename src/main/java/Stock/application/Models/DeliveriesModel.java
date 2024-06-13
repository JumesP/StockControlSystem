package Stock.application.Models;

import Stock.application.SqliteConnection;
import Stock.classes.Deliveries.Deliveries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DeliveriesModel {

    Connection connection;

    public DeliveriesModel() {
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

    public List<Deliveries> FetchDeliveryData() {
        String query = "SELECT * FROM Deliveries ORDER BY Delivery_ID ASC"; //MAKE ALL FUTURE PRODUCTS
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();
            List<int[][]> listOfArrays = new ArrayList<>();
            while (resultSet.next()) {
//                System.out.println(resultSet);
//                int[][] twoDArray = convertTo2DArray(resultSet.getArray("Ordered_Products"));
//                listOfArrays.add(twoDArray);

                System.out.println(listOfArrays);

//                List<Delivered_Items> deliveredProducts = new ArrayList<>();
//                for (String s : resultSet.getArray("Ordered_Products").getArray()) {
//                    System.out.println(s);
//                    Delivered_Items deliveredProduct = new Delivered_Items();
//                    deliveredProduct.setProduct_ID(s[0]);
//                    deliveredProduct.setProduct_Quantity(s[1]);
//                }

                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getString("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }
            resultSet.close();

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // database doesnt work so return empty list to load page
        }
    }

    public List<Deliveries> FetchPastDeliveryData() {
        String query = "SELECT * FROM Deliveries ORDER BY Delivery_ID ASC"; //MAKE ALL Past PRODUCTS
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();
            List<int[][]> listOfArrays = new ArrayList<>();
            while (resultSet.next()) {
                System.out.println(resultSet);
//                int[][] twoDArray = convertTo2DArray(resultSet.getArray("Ordered_Products"));
//                listOfArrays.add(twoDArray);

                System.out.println(listOfArrays);

//                List<Delivered_Items> deliveredProducts = new ArrayList<>();
//                for (String s : resultSet.getArray("Ordered_Products").getArray()) {
//                    System.out.println(s);
//                    Delivered_Items deliveredProduct = new Delivered_Items();
//                    deliveredProduct.setProduct_ID(s[0]);
//                    deliveredProduct.setProduct_Quantity(s[1]);
//                }

                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getString("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void Add() {
//        String query = "insert into Deliveries(delivery_id, delivery_name, delivery_company, ordered_products) VALUES (1,'Chilled Delivery', 'Spencers', json_array(json_array(0,15),json_array(1,20)))"
    }

}
