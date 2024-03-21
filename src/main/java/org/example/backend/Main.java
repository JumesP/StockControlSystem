package org.example.backend;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Loading JDBC Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver Successfully Loaded");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/SupermarketSchema";
        String username = "root";
        String password = "password";
        String query = "select * from Supermarketschema.product";

        Connection connection = null;
        Statement stmt = null;

        try {
            System.out.println("Connecting to the Database");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");

            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1));
                System.out.print(" ");
                System.out.print(resultSet.getString(2));
                System.out.print(" ");
                System.out.print(resultSet.getString(3));
                System.out.print(" ");
                System.out.print(resultSet.getString(4));
                System.out.println(" ");
            }
            stmt.close();

        } catch (SQLException sqlE) {
            System.out.println(sqlE.toString());
        } finally {
            System.out.println("Closing connection...");

            try {
                connection.close();
            } catch (SQLException ignore) {

            }
            connection = null;
        }
    }
}
