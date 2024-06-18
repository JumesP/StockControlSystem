package Stock.classes.Deliveries;

import Stock.application.SqliteConnection;
import Stock.classes.Misc.Clock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.Select;
import static Stock.classes.Misc.Clock.formatDateForUser;
import static java.sql.DriverManager.getConnection;

public class Deliveries {
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static Clock clock = new Clock();

    public static void connection() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    private int Delivery_ID;
    private String Delivery_Name;
    private int Delivery_Date;
    private String Delivery_Company;
    private String Viewable_Delivery_Date;

    public Deliveries(int Delivery_ID, String Delivery_Name, int Delivery_Date, String Delivery_Company) {
        this.Delivery_ID = Delivery_ID;
        this.Delivery_Name = Delivery_Name;
        this.Delivery_Date = Delivery_Date;
        this.Delivery_Company = Delivery_Company;
        this.Viewable_Delivery_Date = formatDateForUser(Delivery_Date);
    }

    public int getDelivery_ID() {
        return Delivery_ID;
    }

    public String getDelivery_Name() {
        return Delivery_Name;
    }

    public int getDelivery_Date() {
        return Delivery_Date;
    }

    public String getDelivery_Company() {
        return Delivery_Company;
    }

    public String getViewable_Delivery_Date() { return Viewable_Delivery_Date; }

    public void setDelivery_ID(Integer Delivery_ID) {
        this.Delivery_ID = Integer.valueOf(Delivery_ID);
    }

    public void setDelivery_Name(String Delivery_Name) {
        this.Delivery_Name = Delivery_Name;
    }

    public void setDelivery_Date(int Delivery_Date) {
        this.Delivery_Date = Delivery_Date;
    }

    public void setDelivery_Company(String Delivery_Company) {
        this.Delivery_Company = Delivery_Company;
    }

    // METHODS
    public void AddDelivery() {
        try {
            connection();
            query = "INSERT INTO Deliveries (Delivery_ID, Delivery_Name, Delivery_Date, Delivery_Company) VALUES ('" + generateDeliveryID() + "', '" + this.Delivery_Name + "', '" + this.Delivery_Date + "', '" + this.Delivery_Company + "')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // STATIC METHODS
    public static List<Deliveries> FetchAllDeliveryData() {
        ResultSet resultSet;
        Statement statement;

        List<Deliveries> data = new ArrayList<>();

        try {
            connection();
            String query = "SELECT * FROM Deliveries ORDER BY Delivery_ID ASC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getInt("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }

            resultSet.close();
            connection.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Deliveries> FetchPastDeliveryData() {
        query = "SELECT * FROM Deliveries WHERE Delivery_Date < " + clock.sortableDate() + " ORDER BY Delivery_ID DESC";
        try {
            connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();

            while (resultSet.next()) {
                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getInt("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            connection.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Deliveries> FetchUpcomingDeliveryData() {
        query = "SELECT * FROM Deliveries WHERE Delivery_Date > " + clock.sortableDate() + " ORDER BY Delivery_ID DESC";
        try {
            connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();

            while (resultSet.next()) {
                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getInt("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            connection.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Deliveries> FetchTodaysDeliveryData() {
        query = "SELECT * FROM Deliveries WHERE Delivery_Date = " + clock.sortableDate() + " ORDER BY Delivery_ID DESC";
        try {
            connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Deliveries> data = new ArrayList<>();

            while (resultSet.next()) {
                data.add(new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getInt("Delivery_Date"), resultSet.getString("Delivery_Company")));
            }
            resultSet.close();
            connection.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Deliveries FetchDeliveryByID(int ID) {
        query = "SELECT * FROM Deliveries WHERE Delivery_ID = " + ID;
        try {
            connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);


            if (resultSet.next()) {
                return new Deliveries(resultSet.getInt("Delivery_ID"), resultSet.getString("Delivery_Name"), resultSet.getInt("Delivery_Date"), resultSet.getString("Delivery_Company"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> IDandProduct() {
        String query;
        Statement statement;
        ResultSet resultSet;

        List<String> products = new ArrayList<>();
        try {
            connection();
            query = "SELECT * FROM Products";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(resultSet.getString("Product_ID") + ": " + resultSet.getString("Product_Name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addProductsToOrder(ArrayList<ArrayList<String>> Ordered_Products) {
        // Add Ordered Products to Order table
        for (ArrayList<String> product : Ordered_Products) {
            String productIDandName = product.get(0);
            String[] productIDandNameArray = productIDandName.split(": ");

            String Product_ID = productIDandNameArray[0];
            String Product_Quantity = product.get(1);
            String Product_Name = productIDandNameArray[1];

            System.out.println("Delivery ID: " + this.Delivery_ID);
            System.out.println("Product ID: " + Product_ID);
            System.out.println("Product Name: " + Product_Name);
            System.out.println("Product Quantity: " + Product_Quantity);

            // inserts the ordered products into the orders table
            try {
                query = "INSERT INTO orders (Delivery_ID, Product_ID, Product_Name, Product_Quantity) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, this.Delivery_ID);
                preparedStatement.setInt(2, Integer.parseInt(Product_ID));
                preparedStatement.setString(3, Product_Name);
                preparedStatement.setInt(4, Integer.parseInt(Product_Quantity));
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // update the ordered products Last Stocked date to delivery date if more recent than current Last Stocked date
            try {
                query = "SELECT Last_Stocked FROM Products WHERE Product_ID = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(Product_ID));
                resultSet = preparedStatement.executeQuery();
                int Last_Stocked = resultSet.getInt(1);
                resultSet.close();
                preparedStatement.close();

                if (Delivery_Date > Last_Stocked) {
                    query = "UPDATE Products SET Last_Stocked = ? WHERE Product_ID = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, Delivery_Date);
                    preparedStatement.setInt(2, Integer.parseInt(Product_ID));
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int generateDeliveryID() {
        query = "SELECT COUNT(*) FROM Deliveries";
        try (ResultSet results = Select(query)){
            return results.getInt(1) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 9999;
    }
}
