package Stock.application.Models;

import Stock.application.SqliteConnection;
import Stock.classes.Deliveries.Deliveries;
import Stock.classes.Misc.Clock;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DeliveriesModel {

    Connection connection;
    Clock clock = new Clock();

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

    public List<Deliveries> FetchFutureDeliveryData() {
        ResultSet resultSet;
        PreparedStatement preparedStatement;

        List<Deliveries> data = new ArrayList<>();

        try {
            String query = "SELECT * FROM Deliveries WHERE Delivery_Date > ? ORDER BY Delivery_Date ASC"; //MAKE ALL FUTURE PRODUCTS
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, clock.sortableDate());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getString("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }

            preparedStatement.close();
            resultSet.close();

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // database doesnt work so return empty list to load page
        }
    }

    public List<Deliveries> FetchPastDeliveryData() {
        ResultSet resultSet;
        PreparedStatement preparedStatement;

        List<Deliveries> data = new ArrayList<>();

        try {
            String query = "SELECT * FROM Deliveries WHERE Delivery_Date < ? ORDER BY Delivery_Date ASC"; //MAKE ALL FUTURE PRODUCTS
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, clock.sortableDate());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet);

                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getString("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Deliveries> FetchAllDeliveryData() {
        ResultSet resultSet;
        Statement statement;

        List<Deliveries> data = new ArrayList<>();

        try {
            String query = "SELECT * FROM Deliveries ORDER BY Delivery_Date ASC"; //MAKE ALL FUTURE PRODUCTS
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
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
