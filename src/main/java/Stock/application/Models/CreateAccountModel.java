package Stock.application.Models;

import Stock.application.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateAccountModel {
    Connection connection;

    public CreateAccountModel() {
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

    public boolean isCreateAccount(String user, String pass, boolean admin) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
//        String query = "INSERT INTO Users (Username, Password, Admin) VALUES (?, ?, ?)";


        String query;
        if (admin) {
            query = "INSERT INTO Users (Username, Password, Admin) VALUES (?, ?, 1)";
        } else {
            query = "INSERT INTO Users (Username, Password, Admin) VALUES (?, ?, 0)";
        }


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            preparedStatement.executeUpdate();
//            System.out.println("Account created successfully");
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
