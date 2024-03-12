import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Admin_user extends User {
    private int id;
    private static int last_id = 0;
    public Admin_user(String username, String password, Boolean availability){
        super(username, password, availability);
        setId(++last_id);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void add_user(ArrayList<User> users){
        System.out.println("Write username: ");
        String username = getSc().nextLine();
        System.out.println("Write password: ");
        String password = getSc().nextLine();
        System.out.println("Write U for normal user and A for admin user: ");
        String user_type = getSc().nextLine();
        if (user_type.equals("U")) {
            Normal_user n = new Normal_user(username, password, false);
            users.add(n);
        }
        if (user_type.equals("A")){
            Admin_user a = new Admin_user(username, password, true);
            users.add(a);
        }
    }
    public void user_remove(ArrayList<Admin_user> users){
        System.out.println("Write the id of the user: ");
        int id = getSc().nextInt();
        for (Admin_user user: users){
            if (id == user.getId()){
                System.out.println("gg");
            }
        }
    }
    public void book_add(ArrayList<Book> books){
        System.out.println("Write the title of the book: ");
        String book_title = getSc().nextLine();
        System.out.println("Write the author of the book: ");
        String book_author = getSc().nextLine();
        System.out.println("Write the genre of the book: ");
        String book_genre = getSc().nextLine();
        System.out.println("Write a short resume of the book: ");
        String book_resume = getSc().nextLine();
        System.out.println("Write the release year of the book: ");
        String book_release_year_str = getSc().nextLine();
        int book_release_year = Integer.parseInt(book_release_year_str);
        System.out.println("Write key words of the book(separate them by comma): ");
        String book_key_words = getSc().nextLine();
        String[] keywordsArray = book_key_words.split(",");
        ArrayList<String> book_keywords_List = new ArrayList<>();
        for (String keyword : keywordsArray) {
            String trimmedKeyword = keyword.trim();
            book_keywords_List.add(trimmedKeyword);
        }
        System.out.println("Write the rating of the book: ");
        String rating = getSc().nextLine();
        double book_rating = Double.parseDouble(rating);
        System.out.println("Write the unique number of the book: ");
        String book_unique_number = getSc().nextLine();
        Book book = new Book(book_title,book_author,book_genre,book_resume,book_release_year,book_keywords_List,book_rating,book_unique_number);
        books.add(book);
    }
    public void book_remove(ArrayList<Book> books){
        System.out.println("Write the unique number of the book: ");
        String book_unique_number = getSc().nextLine();
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getUnique_number().equals(book_unique_number)) {
                iterator.remove(); // Remove the book using the iterator
            }
        }
    }
    public void book_view(ArrayList<Book> books){
        for(Book book: books){
            System.out.println("Title: " + book.getTitle());
        }
    }
    public void book_find(ArrayList<Book> books) {
        System.out.println("Write the unique number of the book: ");
        String book_unique_number = getSc().nextLine();
        Book book_to_find = null;
        for (Book book : books) {
            if (book.getUnique_number().equals(book_unique_number)) {
                book_to_find = book;
                break;
            }
        }
        if (book_to_find != null) {
            System.out.println("Books title: " + book_to_find.getTitle());
        } else {
            System.out.println("Book with unique number '" + book_unique_number + "' not found.");
        }
    }
}
