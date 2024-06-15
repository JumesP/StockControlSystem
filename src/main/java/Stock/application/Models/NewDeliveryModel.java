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

//    public void Add(String Delivery_Name, String Delivery_Date, String Delivery_Company, ArrayList<ArrayList<String>> orderedProducts) {
//        String query;
//        PreparedStatement PreparedStatement;
//        Statement statement;
//        ResultSet resultSet;
//
//        try {
//            query = "SELECT COUNT(*) FROM Deliveries";
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(query);
//            Integer Delivery_ID = resultSet.getInt(1);
//            resultSet.close();
//
//            query = "INSERT INTO Deliveries (Delivery_ID, Delivery_Name, Delivery_Date, Delivery_Company) VALUES (?, ?, ?, ?)";
//            PreparedStatement = connection.prepareStatement(query);
//            PreparedStatement.setString(1, String.valueOf(Delivery_ID));
//            PreparedStatement.setString(2, Delivery_Name);
//            PreparedStatement.setString(3, Delivery_Date);
//            PreparedStatement.setString(4, Delivery_Company);
//            PreparedStatement.executeUpdate();
//            PreparedStatement.close();
//            connection.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public List<String> getProducts() {
        //Get all products from the database, used to name the products in the dropdown menu on the New Delivery page.
        List<String> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                products.add(resultSet.getString("Product_ID") + ": " + resultSet.getString("Product_Name"));
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

    public void Add(String Delivery_Name, String Delivery_Company, int Delivery_Date, ArrayList<ArrayList<String>> Ordered_Products) {
        String query;
        PreparedStatement PreparedStatement;
        Statement statement;
        ResultSet resultSet;

        System.out.println("Ordered Products" + Ordered_Products);

        try {
            // Delivery ID
            query = "SELECT COUNT(*) FROM Deliveries";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int Delivery_ID = resultSet.getInt(1);
            resultSet.close();

            // Add Delivery to Deliveries table
            query = "INSERT INTO deliveries (Delivery_Name, Delivery_ID, Delivery_Company, Delivery_Date) VALUES (?, ?, ?, ?)";
            PreparedStatement = connection.prepareStatement(query);
            PreparedStatement.setString(1, Delivery_Name);
            PreparedStatement.setInt(2, Delivery_ID);
            PreparedStatement.setString(3, Delivery_Company);
            PreparedStatement.setInt(4, Delivery_Date);
            System.out.println("PreparedStatement value: " + PreparedStatement);
            PreparedStatement.executeUpdate();
            PreparedStatement.close();

            // Add Ordered Products to Order table
            for (ArrayList<String> product : Ordered_Products) {

                String productIDandName = product.get(0);
                String[] productIDandNameArray = productIDandName.split(": ");

                String Product_ID = productIDandNameArray[0];
                String Product_Quantity = product.get(1);
                String Product_Name = productIDandNameArray[1];

                System.out.println("Delivery ID: " + Delivery_ID);
                System.out.println("Product ID: " + Product_ID);
                System.out.println("Product Name: " + Product_Name);
                System.out.println("Product Quantity: " + Product_Quantity);

                // inserts the ordered products into the orders table
                try {
                    query = "INSERT INTO orders (Delivery_ID, Product_ID, Product_Name, Product_Quantity) VALUES (?, ?, ?, ?)";
                    PreparedStatement = connection.prepareStatement(query);
                    PreparedStatement.setInt(1, Delivery_ID);
                    PreparedStatement.setInt(2, Integer.parseInt(Product_ID));
                    PreparedStatement.setString(3, Product_Name);
                    PreparedStatement.setInt(4, Integer.parseInt(Product_Quantity));
                    PreparedStatement.executeUpdate();
                    PreparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // update the ordered products Last Stocked date to delivery date if more recent than current Last Stocked date
                try {
                    query = "SELECT Last_Stocked FROM Products WHERE Product_ID = ?";
                    PreparedStatement = connection.prepareStatement(query);
                    PreparedStatement.setInt(1, Integer.parseInt(Product_ID));
                    resultSet = PreparedStatement.executeQuery();
                    int Last_Stocked = resultSet.getInt(1);
                    resultSet.close();
                    PreparedStatement.close();

                    if (Delivery_Date > Last_Stocked) {
                        query = "UPDATE Products SET Last_Stocked = ? WHERE Product_ID = ?";
                        PreparedStatement = connection.prepareStatement(query);
                        PreparedStatement.setInt(1, Delivery_Date);
                        PreparedStatement.setInt(2, Integer.parseInt(Product_ID));
                        PreparedStatement.executeUpdate();
                        PreparedStatement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ProductAdd(int Product_ID, int Product_Quantity) {
        //Add a products updated quantity to the database
        String query;
        PreparedStatement PreparedStatement;
        ResultSet resultSet;

        try {
            query = "SELECT Product_Quantity FROM Products WHERE Product_ID = ?";
            PreparedStatement = connection.prepareStatement(query);
            PreparedStatement.setInt(1, Product_ID);
            resultSet = PreparedStatement.executeQuery();
            int ProductQuantity = resultSet.getInt(1);
            resultSet.close();
            PreparedStatement.close();

            query = "UPDATE Products SET Product_Quantity = ? WHERE Product_ID = ?";
            PreparedStatement = connection.prepareStatement(query);
            PreparedStatement.setInt(1, ProductQuantity + Product_Quantity);
            PreparedStatement.setInt(2, Product_ID);
            PreparedStatement.executeUpdate();
            PreparedStatement.close();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
