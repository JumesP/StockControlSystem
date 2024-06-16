package Stock.classes.Users;

import java.util.ArrayList;
import java.util.List;

public class PendingAdmins extends Admins {
    private Boolean isApproved = false;

    public PendingAdmins(String username, String password) {
        super(username, password);
        this.isApproved = isApproved;
    }

    // GETTERS AND SETTERS
    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    // METHODS



    // STATIC METHODS
    public static List<PendingAdmins> FetchPending() {
        // Fetch all pending admin accounts
        query = "SELECT * FROM Users WHERE Admin = 1 AND Approved = 0";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<PendingAdmins> unApprovedAdmins = new ArrayList<>();
            while (resultSet.next()) {
                int user_id = resultSet.getInt("User_ID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");

                PendingAdmins pendingAdmins = new PendingAdmins(username, password);
                unApprovedAdmins.add(pendingAdmins);
            }
            resultSet.close();
            preparedStatement.close();
            return unApprovedAdmins;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
