import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Normal_user extends User implements Serializable {


    private int id;
    private static int last_id = 0;
    public Normal_user(String username, String password, Boolean availability){
        super(username, password, availability);
        setId(++last_id);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void book_sort(ArrayList<Book> books, String tag, String order){
        if(!books.isEmpty()) {
            switch (tag.toLowerCase()) {
                case "title":
                    gnomeSortBooks(books, Comparator.comparing(book -> book.getTitle().toLowerCase()), order);
                    break;
                case "author":
                    gnomeSortBooks(books, Comparator.comparing(book -> book.getAuthor().toLowerCase()), order);
                    break;
                case "year":
                    gnomeSortBooks(books, Comparator.comparingInt(Book::getRealise_year), order);
                    break;
                case "rating":
                    gnomeSortBooks(books, Comparator.comparingDouble(Book::getRating), order);
                    break;
                default:
                    System.out.println("Invalid option: " + tag);
                    return;
            }
            System.out.println("Sorted Books:");
            for (Book book : books) {
                System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getRealise_year() + ", " + book.getRating());
            }
        }
    }
    private static void gnomeSortBooks(ArrayList<Book> books, Comparator<Book> comparator, String order) {
        int index = 0;
        while (index < books.size()) {
            if (index == 0 || (order.equals("asc") && comparator.compare(books.get(index), books.get(index - 1)) >= 0)
                    || (order.equals("desc") && comparator.compare(books.get(index), books.get(index - 1)) <= 0)) {
                index++;
            } else {
                Book temp = books.get(index);
                books.set(index, books.get(index - 1));
                books.set(index - 1, temp);
                index--;
            }
        }
    }
    public void book_find(ArrayList<Book> books, String tag ,String word) {
        Book book_to_find = null;
        for (Book book : books) {
            if (book.getUnique_number().equals(word)) {
                book_to_find = book;
                break;
            }
            if (book.getTitle().equals(word)){
                book_to_find=book;
                break;
            }
            if (book.getAuthor().equals(word)){
                book_to_find = book;
                break;
            }

        }
        if (book_to_find != null) {
            System.out.println("Book's title: " + book_to_find.getTitle());
            System.out.println("Book's author: " + book_to_find.getAuthor());
            System.out.println("Book's unique number: "+ book_to_find.getUnique_number());
        }
        else {
            System.out.println("Book with " + tag + " is not found.");
        }
    }
    public void book_all(ArrayList<Book> books){
        if (!books.isEmpty()){
            for(Book book:books) {
                System.out.println("Tittle: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Unique number: " + book.getUnique_number());
            }
        }else{
            System.out.println("There is no books in the library.");
        }
    }

    public void book_info(ArrayList<Book> books, String unique_number){
        Book book_info = null;
        for(Book book:books){
            if(book.getUnique_number().equals(unique_number)){
                book_info=book;
            }
        }
        if(book_info!=null){
            System.out.println("Tittle: " + book_info.getTitle());
            System.out.println("Author: " + book_info.getAuthor());
            System.out.println("Genre: "+ book_info.getGenre());
            System.out.println("Resume: " + book_info.getResume());
            System.out.println("The release year is " + book_info.getRealise_year());
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < book_info.getKey_words().size(); i++) {
                output.append(book_info.getKey_words().get(i));
                if (i < book_info.getKey_words().size() - 1) { // Add a comma if it's not the last element
                    output.append(", ");
                }
            }
            System.out.println("Key words: " + output);
            System.out.println("Rating: " + book_info.getRating());
            System.out.println("Unique number: " + book_info.getUnique_number());
        }
        else{
            System.out.println("Book with this unique number is not found in our library.");
        }
    }
    public void book_view(ArrayList<Book> books){
        if(!books.isEmpty()) {
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle());
            }
        }
        else{
            System.out.println("There is no books in the library.");
        }
    }
    public User logout(Non_user user){
        User current_user;
        current_user = (Non_user) user;
        return current_user;
    }
}
