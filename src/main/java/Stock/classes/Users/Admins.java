package Stock.classes.Users;

import Stock.classes.Users.Users;

public class Admins extends Users{
    private Boolean isAdmin;

    public Admins(String username, String password, Boolean isAdmin) {
        super(username, password);
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
