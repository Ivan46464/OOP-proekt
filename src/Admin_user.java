import java.util.ArrayList;
import java.util.Scanner;

public class Admin_user extends User {
    public Admin_user(String username, String password, Boolean availability){
        super(username, password, availability);
    }
    public void add_user(ArrayList<User> users){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write username: ");
        String username = sc.nextLine();
        System.out.println("Write password: ");
        String password = sc.nextLine();
        System.out.println("Write U for normal user and A for admin user: ");
        String user_type = sc.nextLine();
        if (user_type.equals("U")) {
            Normal_user n = new Normal_user(username, password, false);
            users.add(n);
        }
        if (user_type.equals("A")){
            Admin_user a = new Admin_user(username, password, true);
            users.add(a);
        }
    }
    public void user_remove(ArrayList<User> users){

    }
}
