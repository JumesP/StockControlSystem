package Stock.classes.Deliveries;

import Stock.application.SqliteConnection;
import Stock.classes.Misc.Clock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Stock.classes.Misc.Clock.formatDateForUser;

public class Deliveries {
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static Clock clock = new Clock();

    private Integer Delivery_ID;
    private String Delivery_Name;
    private String Delivery_Date;
    private String Delivery_Company;

    public Deliveries(Integer Delivery_ID, String Delivery_Name, String Delivery_Date, String Delivery_Company) {
        this.Delivery_ID = Delivery_ID;
        this.Delivery_Name = Delivery_Name;
        this.Delivery_Date = Delivery_Date;
        this.Delivery_Company = Delivery_Company;

        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public Integer getDelivery_ID() {
        return Delivery_ID;
    }

    public String getDelivery_Name() {
        return Delivery_Name;
    }

    public String getDelivery_Date() {
        return Delivery_Date;
    }

    public String getDelivery_Company() {
        return Delivery_Company;
    }

    public void setDelivery_ID(Integer Delivery_ID) {
        this.Delivery_ID = Integer.valueOf(Delivery_ID);
    }

    public void setDelivery_Name(String Delivery_Name) {
        this.Delivery_Name = Delivery_Name;
    }

    public void setDelivery_Date(String Delivery_Date) {
        this.Delivery_Date = Delivery_Date;
    }

    public void setDelivery_Company(String Delivery_Company) {
        this.Delivery_Company = Delivery_Company;
    }

    // METHODS

    public void AddDelivery(String Delivery_Name, String Delivery_Date, String Delivery_Company) {
        String query;
        Statement statement;

        try {
            query = "INSERT INTO Deliveries (Delivery_Name, Delivery_Date, Delivery_Company) VALUES ('" + Delivery_Name + "', '" + Delivery_Date + "', '" + Delivery_Company + "')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> IDandProduct() {
        String query;
        Statement statement;
        ResultSet resultSet;

        List<String> products = new ArrayList<>();
        try {
            query = "SELECT * FROM Products";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(resultSet.getString("Product_ID") + ": " + resultSet.getString("Product_Name"));
            }
            resultSet.close();
            statement.close();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    // STATIC METHODS

    public static List<Deliveries> FetchAllDeliveryData() {
        ResultSet resultSet;
        Statement statement;

        List<Deliveries> data = new ArrayList<>();

        try {
            String query = "SELECT * FROM Deliveries ORDER BY Delivery_Date ASC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String formattedDate = formatDateForUser(resultSet.getInt("Delivery_Date"));
                System.out.println("Formatted Date: " + formattedDate);

                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), formattedDate, resultSet.getString("Delivery_Company")));
            }

            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Deliveries> FetchPastDeliveryData() {
        query = "SELECT * FROM Deliveries WHERE Delivery_Date < " + clock.sortableDate();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();

            while (resultSet.next()) {
                String formattedDate = formatDateForUser(resultSet.getInt("Delivery_Date"));
                System.out.println("Formatted Date: " + formattedDate);

                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), formattedDate, resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Deliveries> FetchUpcomingDeliveryData() {
        query = "SELECT * FROM Deliveries WHERE Delivery_Date > " + clock.sortableDate();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();

            while (resultSet.next()) {
                String formattedDate = formatDateForUser(resultSet.getInt("Delivery_Date"));
                System.out.println("Formatted Date: " + formattedDate);

                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), formattedDate, resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Deliveries> FetchTodaysDeliveryData() {
        query = "SELECT * FROM Deliveries WHERE Delivery_Date = " + clock.sortableDate();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();

            while (resultSet.next()) {
                String formattedDate = formatDateForUser(resultSet.getInt("Delivery_Date"));
                System.out.println("Formatted Date: " + formattedDate);

                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), formattedDate, resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Deliveries FetchDeliveryByID(int ID) {
        query = "SELECT * FROM Deliveries WHERE Delivery_ID = " + ID;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println(resultSet.getString("Delivery_Name"));
                return new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), formatDateForUser(resultSet.getInt("Delivery_Date")), resultSet.getString("Delivery_Company"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
