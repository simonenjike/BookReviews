package ibb.bookreviews.model;

/**
 * Die Klasse repräsentiert ein Buch, das bewertet wird. Es zeigt Buchdaten an.
 * @author Simone Njike
 * @date 21.10.2025
 */
public class Book {
    
    private Long id;            // Buchnummer
    private String title;       // Buchtitel
    private String author;   // Name des Autors
    private String isbn;      // ISBN-Nummer
    private String genre;    // Genre (Roman, Thriller, ...)
    private int year;           // Erscheinungsjahr

    // Konstruktoren
    public Book() { }

    public Book(Long id, String title, String author, String isbn, String genre, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }
    
    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return title + " von " + author + " ( " + year + " ) ";
    }        
    
}