import java.util.ArrayList;

public class Book {


    private String author;

    private String title;



    private String genre;

    private String resume;


    private int realise_year;



    private ArrayList<String> key_words =new ArrayList<>();

    private double rating;
    private String unique_number;
    public Book(String author, String title, String genre, String resume, int realise_year, ArrayList<String> key_words,double rating, String unique_number){
        setAuthor(author);
        setTitle(title);
        setGenre(genre);
        setResume(resume);
        setRealise_year(realise_year);
        setKey_words(key_words);
        setRating(rating);
        setUnique_number(unique_number);
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if ("".equals(author)) {
            System.out.println(author);
        }
        else {
            this.author = author;
        }
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
    public int getRealise_year() {
        return realise_year;
    }

    public void setRealise_year(int realise_year) {
        this.realise_year = realise_year;
    }
    public ArrayList<String> getKey_words() {
        return key_words;
    }

    public void setKey_words(ArrayList<String> key_words) {
        this.key_words = key_words;
    }
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getUnique_number() {
        return unique_number;
    }

    public void setUnique_number(String unique_number) {
        this.unique_number = unique_number;
    }
}
