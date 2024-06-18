package Stock.classes.Users;

import Stock.application.SqliteConnection;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static Stock.application.SqliteConnection.Select;
import static Stock.application.SqliteConnection.SelectAd;

public class Users {
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    public static void connection() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }


    private int User_ID;
    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // GETTERS
    public int getUser_ID() {
        return User_ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // SETTERS
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // METHODS
    public Boolean isCreateAccount(String username, String password, Boolean admin) {
        // Create and account using the username and password and return success
        connection();
        if (admin) {
            query = "INSERT INTO Users (User_ID, Username, Password, Admin, Approved) VALUES (?, ?, ?, 1, 0)";
            // requests approval, but is stored as admin
        } else {
            query = "INSERT INTO Users (User_ID, Username, Password, Admin, Approved) VALUES (?, ?, ?, 0, 1)";
            // does not request approval, but is stored as user
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, generateUserID());
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            connection.close();
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

    public Boolean isLogin() {
        // Login using the username and password and return success
        query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        try(ResultSet results = SelectAd(query, username, password)) {
            if (results.next()) {
                FileWriter myWriter = new FileWriter("src/main/java/Stock/backend/cookie.txt");
                if (results.getString("Admin").equals("1")) {
                    myWriter.write("admin");
                } else {
                    myWriter.write("user");
                }
                myWriter.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }

//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, this.username);
//            preparedStatement.setString(2, this.password);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                FileWriter myWriter = new FileWriter("src/main/java/Stock/backend/cookie.txt");
//                if (resultSet.getString("Admin").equals("1")) {
//                    myWriter.write("admin");
//                } else {
//                    myWriter.write("user");
//                }
//                myWriter.close();
//                connection.close();
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    // STATIC METHODS
    public static int generateUserID() {
        // Generate a new User_ID
        connection();
        query = "SELECT COUNT(*) FROM users";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int User_ID = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            connection.close();
            return User_ID;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
