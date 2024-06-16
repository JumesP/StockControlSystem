package Stock.classes.Users;

public class PendingAdmins extends Admins {
    private Boolean isApproved = false;

    public PendingAdmins(int User_ID, String username, String password, Boolean isAdmin, Boolean isApproved) {
        super(User_ID, username, password, isAdmin);
        this.isApproved = isApproved;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    // METHODS

    public void FetchPending() {

    }


    // STATIC METHODS






}
