package Stock.classes.Users;

import Stock.classes.Users.Users;

public class Admins extends Users{
    private Boolean isAdmin = true;

    public Admins(int User_ID, String username, String password, Boolean isAdmin) {
        super(User_ID, username, password);
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    // METHODS



    // STATIC METHODS
    public static void ApproveAdmin(int ID) {
        // Approve an admin account
    }




}

