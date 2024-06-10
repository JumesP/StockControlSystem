package Stock.application.Models;

import Stock.application.SqliteConnection;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPageModel {
    Connection connection;

    public LoginPageModel() {
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

    public boolean isLogin(String user, String pass) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                FileWriter myWriter = new FileWriter("src/main/java/Stock/backend/cookie.txt");
                if (resultSet.getString("Admin").equals("1")) {
                    myWriter.write("admin");
                } else {
                    myWriter.write("user");
                }
                myWriter.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                return false;
            }
        }
    }


}
