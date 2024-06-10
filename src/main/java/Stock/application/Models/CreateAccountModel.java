package Stock.application.Models;

import Stock.application.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

        try {
            String query = "SELECT COUNT(*) FROM users";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Integer User_ID = resultSet.getInt(1);
            resultSet.close();
            statement.close();

            if (admin) {
                query = "INSERT INTO Users (User_ID, Username, Password, Admin, Approved) VALUES (?, ?, ?, 1, 0)";
                // requests approval, but is stored as admin
            } else {
                query = "INSERT INTO Users (User_ID, Username, Password, Admin, Approved) VALUES (?, ?, ?, 0, 1)";
                // does not request approval, but is stored as user
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(User_ID));
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, pass);

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
