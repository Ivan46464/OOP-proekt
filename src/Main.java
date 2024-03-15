import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Book> books = new ArrayList<>();
            ArrayList<Normal_user> normalUsers = new ArrayList<>();
            Admin_user admin_user = new Admin_user("admin", "i<3c++", true);
            Non_user non_user = new Non_user("","",false);
            User current_user;
            current_user = admin_user;
            String filename = null;
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start");
            Process process = processBuilder.start();
            System.out.println("Opened a new command prompt.");
            Boolean fileOpened = false;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            do {

                System.out.print(">> ");
                userInput = reader.readLine();
                userInput = userInput.trim();
                String[] words = userInput.split("\\s+");
                switch (words[0]) {
                    case "open":
                        if (!fileOpened && words.length == 2) {
                            filename = words[1];
                            FileHandler.readFromFile(filename, books, normalUsers);
                            fileOpened = true;
                        } else if (fileOpened) {
                            System.out.println("A file is already opened.");
                        } else {
                            System.out.println("Type help if you need to see the commands.");
                        }
                        break;
                    case "save":
                        if (fileOpened) {
                            FileHandler.writeToFile(filename, books, normalUsers);
                        } else {
                            System.out.println("No file is currently opened.");
                        }
                        break;
                    case "saveas":
                        if (fileOpened) {
                            if (words.length > 1) {
                                String dir = words[1];
                                FileHandler.writeToFile_dir(dir, filename, books, normalUsers);
                                System.out.println("Successfully saved " + filename + " in " + dir);
                            } else {
                                System.out.println("Specify the directory where you want to save the file.");
                            }
                        } else {
                            System.out.println("No file is currently opened.");
                        }
                        break;
                    case "close":
                        if (fileOpened) {
                            FileHandler.closeFile(filename);
                            fileOpened = false;
                            filename = null;
                            books.clear();
                            normalUsers.clear();
                        } else {
                            System.out.println("No file is currently opened.");
                        }
                        break;
                    case "help":
                        System.out.println("login You login in your account.");
                        System.out.println("logout You logout of your account.");
                        System.out.println("open <filename> You open a file and its content.");
                        System.out.println("save Saves the changes in the file you are in.");
                        System.out.println("saveas Saves the file in the directory you want.");
                        System.out.println("help Shows you all the commands and what they do.");
                        System.out.println("books all Shows title, author, genre and unique number of all books.");
                        System.out.println("books info <unique number> Shows the information for the book with the specific isbn(unique number).");
                        System.out.println("books find <option> <option_string> Finds the book with the <option>, which is one of title, author, tag, <option_string> is the search criteria value, may contain spaces.");
                        System.out.println("books sort <option> [asc | desc] <option> is one of title, author, year, rating asc means ascending sort (default) and des means descending sort.");
                        System.out.println("books view Shows all the books");
                        System.out.println("books add You add a book.");
                        System.out.println("books remove You remove a book.");
                        System.out.println("users add <user> <password> Adds a new user with username <user> and password <password>. The user and his password are saved to a file.");
                        System.out.println("users remove <user_id> Deletes the user with username id <user_id> from the file.");
                        break;
                    case "login":
                        if(words.length<2) {
                            if (current_user instanceof Non_user) {
                                current_user = ((Non_user) current_user).login(normalUsers, admin_user, non_user);
                            } else {
                                System.out.println("You have already logged in.");
                            }
                        }
                        break;
                    case "logout":
                        if(current_user instanceof Admin_user){
                            current_user = ((Admin_user) current_user).logout(non_user);
                        }
                        else if (current_user instanceof Normal_user){
                            current_user = ((Normal_user) current_user).logout(non_user);
                        }
                        break;
                    case "exit":
                        // Handle exit command
                        break;
                    case "books":
                        if (words.length > 1) {
                            switch (words[1]) {
                                case "all":
                                    if(current_user instanceof Admin_user) {
                                        ((Admin_user)current_user).book_all(books);
                                    }
                                    else if(current_user instanceof Normal_user){
                                        ((Normal_user)current_user).book_all(books);
                                    }
                                    else{
                                        System.out.println("You should be logged in to use this command.");
                                    }
                                    break;
                                case "find":
                                    if (words.length > 3) {
                                        String tag = words[2];
                                        StringBuilder option = new StringBuilder();
                                        for (int i = 3; i < words.length; i++) {
                                            option.append(words[i]);
                                            if (i < words.length - 1) {
                                                option.append(" ");
                                            }
                                        }
                                        if(current_user instanceof Admin_user) {
                                            ((Admin_user)current_user).book_find(books, tag, option.toString());
                                        }
                                        else if(current_user instanceof Normal_user){
                                            ((Normal_user)current_user).book_find(books, tag, option.toString());
                                        }
                                        else{
                                            System.out.println("You should be logged in to use this command.");
                                        }
                                    } else {
                                        System.out.println("Your syntax is wrong. Write 'help' to see how.");
                                    }
                                    break;
                                case "sort":
                                    if (words.length > 2) {
                                        String sortOption = words[2];
                                        String sortOrder = "asc"; // Default to ascending order
                                        if (words.length > 3) {
                                            sortOrder = words[3];
                                            if(current_user instanceof Admin_user) {
                                                ((Admin_user)current_user).book_sort(books, sortOption, sortOrder);
                                            }
                                            else if(current_user instanceof Normal_user){
                                                ((Normal_user)current_user).book_sort(books, sortOption, sortOrder);
                                            }
                                            else{
                                                System.out.println("You should be logged in to use this command.");
                                            }
                                            break;
                                        }
                                        if(current_user instanceof Admin_user) {
                                            ((Admin_user)current_user).book_sort(books, sortOption, sortOrder);
                                        }
                                        else if(current_user instanceof Normal_user){
                                            ((Normal_user)current_user).book_sort(books, sortOption, sortOrder);
                                        }
                                        else{
                                            System.out.println("You should be logged in to use this command.");
                                        }
                                    } else {
                                        System.out.println("Your syntax os wrong write help to see how.");
                                    }
                                    break;
                                case "info":
                                    if (words.length > 2) {
                                        String isbnValue = words[2];
                                        if(current_user instanceof Admin_user) {
                                            ((Admin_user)current_user).book_info(books,isbnValue);
                                        }
                                        else if(current_user instanceof Normal_user){
                                            ((Normal_user)current_user).book_info(books,isbnValue);
                                        }
                                        else{
                                            System.out.println("You should be logged in to use this command.");
                                        }
                                    } else {
                                        System.out.println("Your syntax os wrong write help to see how.");
                                    }
                                    break;
                                case "view":
                                    if(current_user instanceof Admin_user) {
                                        ((Admin_user)current_user).book_view(books);
                                    }
                                    else if(current_user instanceof Normal_user){
                                        ((Normal_user)current_user).book_view(books);
                                    }
                                    else{
                                        System.out.println("You should be logged in to use this command.");
                                    }
                                    break;
                                case "add":
                                    admin_user.book_add(books);
                                    break;
                                case "remove":
                                    System.out.println("Write the unique number of the book: ");
                                    String unique_number = reader.readLine();
                                    admin_user.book_remove(books,unique_number);
                                    break;
                                default:
                                    System.out.println("Unknown command type help.");
                                    break;
                            }
                        } else {
                            System.out.println("Unknown command type help.");
                        }
                        break;
                    case "users":

                        if (words.length > 1) {
                            switch (words[1]) {
                                case "add":
                                    if (words.length > 3) {
                                        String username = words[2];
                                        String password = words[3];
                                        if(current_user instanceof Admin_user) {
                                            ((Admin_user)current_user).add_user(normalUsers, username, password);
                                        }
                                        else{
                                            System.out.println("You should be admin to add users.");
                                        }
                                    } else {
                                        System.out.println("Your syntax os wrong write help to see how.");
                                    }
                                    break;
                                case "remove":
                                    if (words.length > 2) {
                                        String id = words[2];
                                        int id_int = Integer.parseInt(id);
                                        admin_user.user_remove(normalUsers,id_int);
                                    } else {
                                        System.out.println("Your syntax os wrong write help to see how.");
                                    }
                                    break;
                                default:
                                    System.out.println("Unknown command type help.");
                                    break;
                            }
                        } else {
                            System.out.println("Incomplete command.");
                        }
                        break;
                    default:
                        System.out.println("Unknown command type help.");
                        break;
                }

            } while (!userInput.equals("exit"));

            process.waitFor();

            System.out.println("Command prompt closed.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}