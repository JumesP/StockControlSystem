package Stock.classes.Users;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.Select;

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
        try (ResultSet results = Select(query)) {

            List<PendingAdmins> unApprovedAdmins = new ArrayList<>();

            while (results.next()) {
                String username = results.getString("Username");
                String password = results.getString("Password");

                PendingAdmins pendingAdmins = new PendingAdmins(username, password);
                unApprovedAdmins.add(pendingAdmins);
            }
            return unApprovedAdmins;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
