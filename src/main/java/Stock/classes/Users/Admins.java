package Stock.classes.Users;

import Stock.classes.Users.Users;

public class Admins extends Users{
    private Boolean isAdmin = true;

    public Admins(String username, String password) {
        super(username, password);
        this.isAdmin = isAdmin;
    }

    // GETTERS AND SETTERS
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    // METHODS
    public void approveAdmin() {
        // Approve an admin account
        query = "UPDATE Users SET Approved = 1 WHERE User_ID = " + this.getUser_ID();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // STATIC METHODS
    public static void approveAdminByID(int ID) {
        // Approve an admin account
        query = "UPDATE Users SET Approved = 1 WHERE User_ID = " + ID;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

