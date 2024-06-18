package Stock.application;
import java.io.Closeable;
import java.sql.*;
import org.sqlite.JDBC;

public class SqliteConnection {
    public static Connection Connector() {
        System.out.println("Connection Started");
        try {
            Class.forName("org.sqlite.JDBC");
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\Stock\\backend\\StockDBv2");  // Windows
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/Stock/backend/StockDBv2"); // Mac
            System.out.println("Connection Success");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Connection Failed");
            return null;
        }
    }

    public static void connection() {
        Connection connection = Connector();
        if (connection == null) System.exit(1);
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Insert(String query) {
        Connection connection = Connector();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet Select(String query) {
        Connection connection = Connector();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet SelectAd(String query, String Username, String Password) {
        Connection connection = Connector();
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void Update(String query) {
        Connection connection = Connector();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Delete(String query) {
        Connection connection = Connector();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

