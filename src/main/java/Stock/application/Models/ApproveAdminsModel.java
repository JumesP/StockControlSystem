package Stock.application.Models;

import Stock.application.SqliteConnection;
import Stock.classes.Users.PendingAdmins;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApproveAdminsModel {
    //search for users that are not pending admin approval
    Connection connection;

    public ApproveAdminsModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

//    public List<PendingAdmins> FetchData() {
//        try {
//            String query = "SELECT * FROM Users WHERE Admin = 1 AND Approved = 0";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            List<PendingAdmins> unApprovedAdmins = new ArrayList<>();
//            while (resultSet.next()) {
//                int user_id = resultSet.getInt("User_ID");
//                String username = resultSet.getString("Username");
//                String password = resultSet.getString("Password");
//
//                PendingAdmins pendingAdmins = new PendingAdmins(user_id, username, password, true, false);
//                unApprovedAdmins.add(pendingAdmins);
//            }
//            resultSet.close();
//            statement.close();
//            return unApprovedAdmins;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    //approve pending users
    public void ApproveAdmins(int user_id) {
        try {
            String query = "UPDATE Users SET Approved = 1 WHERE User_ID = " + user_id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

