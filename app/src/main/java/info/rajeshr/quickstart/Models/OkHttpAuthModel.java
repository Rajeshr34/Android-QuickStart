package info.rajeshr.quickstart.Models;


public class OkHttpAuthModel {

    private final String userName;
    private final String password;

    public OkHttpAuthModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
