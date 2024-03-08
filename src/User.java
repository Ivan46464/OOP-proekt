public abstract class User {


    private String username;
    private String password;


    private Boolean availability;
    public  User(String username, String password, Boolean availability){
        setUsername(username);
        setPassword(password);
        setAvailability(availability);
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

}
