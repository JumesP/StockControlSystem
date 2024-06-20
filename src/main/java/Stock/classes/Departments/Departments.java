package Stock.classes.Departments;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.Select;

public class Departments {
    static Connection connection;
    static String query;
    static ResultSet resultSet;
    static Statement statement;
    static PreparedStatement preparedStatement;

    static {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    private String Department_Name;
    private String Department_ID;
    private String Parent_Department;

    public Departments(String Department_Name, String Department_ID, String Parent_Department) {
        this.Department_Name = Department_Name;
        this.Department_ID = Department_ID;
        this.Parent_Department = Parent_Department;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public String getDepartment_ID() {
        return Department_ID;
    }

    public String getParent_Department() {
        return Parent_Department;
    }



    public void setDepartment_Name(String Department_Name) {
        this.Department_Name = Department_Name;
    }

    public void setDepartment_ID(String Department_ID) {
        this.Department_ID = Department_ID;
    }

    public void setParent_Department(String Parent_Department) {
        this.Parent_Department = Parent_Department;
    }


    // METHODS








    // STATIC METHODS
    public static List<String> listOfDepartments() {
        query = "SELECT * FROM Departments";
        List<String> departments = new ArrayList<>();
        try (ResultSet results = Select(query)) {
            while (results.next()) {
                departments.add(results.getString("Department_Name"));
            }
            return departments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static String[] listOfParentDepertments() {
        return new String[]{"Chilled", "Frozen", "Frozen", "Household", "Entertainment", "Toiletries"};
    }

    public static int getDepartmentID(String departmentName) {
        query = "SELECT Department_ID FROM Departments WHERE Department_Name = " + departmentName;
        try (ResultSet results = Select(query)) {
            return results.getInt("Department_ID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
