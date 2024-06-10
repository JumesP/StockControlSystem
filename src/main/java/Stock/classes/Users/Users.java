package Stock.classes.Users;

public class Users {
    private int User_ID;
    private String username;
    private String password;

    public Users(int User_ID, String username, String password) {
        this.User_ID = User_ID;
        this.username = username;
        this.password = password;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
