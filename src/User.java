import java.util.Scanner;

public abstract class User {


    private String username;
    private String password;

    private Boolean availability;

    private Scanner sc;
    public  User(String username, String password, Boolean availability){
        setUsername(username);
        setPassword(password);
        setAvailability(availability);
        Scanner sc = new Scanner(System.in);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

}
