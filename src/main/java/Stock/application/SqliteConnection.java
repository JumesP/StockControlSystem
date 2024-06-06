package Stock.application;
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
}
