import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import org.jline.reader.LineReader;
import org.jline.reader.MaskingCallback;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
public class Non_user extends User {

    public Non_user(String username, String password, Boolean availability) {
        super(username, password, availability);
    }

    public User login(ArrayList<Normal_user> normal_users, Admin_user admin_user, Non_user user) {
        Scanner scanner = new Scanner(System.in);
        User current_user = null;
        Terminal terminal = TerminalBuilder.terminal();
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .build();

        System.out.println("Write your username: ");
        String username = reader.readLine();

        System.out.println("Write your password: ");
        String password = reader.readLine(new Character('*'));
        for (Normal_user normal_user : normal_users) {
            if (username.equals(normal_user.getUsername()) & password.equals(normal_user.getPassword())) {
                current_user = (Normal_user) normal_user;
                System.out.println("Welcome " + current_user.getUsername());
                return current_user;

            }

        }
        if (current_user == null) {
            if (admin_user.getUsername().equals(username) & admin_user.getPassword().equals(password)) {
                current_user = (Admin_user) admin_user;
                System.out.println("Welcome " + current_user.getUsername());
            } else {
                System.out.println("There is no user with such password or username.");
                current_user = (Non_user) user;
            }
        }
        return current_user;
    }
    private static String readPasswordWithMask(Scanner scanner) {
        System.out.print("Password: ");
        String password = "";
        Console console = System.console();
        if (console == null) {
            // Console is not available, fallback to normal input
            return scanner.nextLine();
        }

        while (true) {
            char c = console.readPassword()[0];
            if (c == '\n' || c == '\r') {
                break;
            }
            password += c;
            System.out.print("*"); // Print asterisk instead of the actual character
        }
        return password;
    }
}
