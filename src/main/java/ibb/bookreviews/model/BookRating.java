package ibb.bookreviews.model;

import java.io.Serializable;

/**
 * Diese Klasse speichert eine Bewertungen je Buch - eines Benutzers.
 * @author Simone Njike
 * @date 21.10.2025
 */
public class BookRating implements Serializable {
        
    private Long id;                    // ID der Bewertung
    private Long bookId;            // Referenz auf das Buch; Verbindung zwischen Bewertung und Buch
    private String userName;     // Benutzername (ID) des Bewerters
    private int rating;                // Bewertung von 1 bis 5 Sterne
    private String comment;       // Freitext-Kommentar    

    // Konstruktoren
    public BookRating() { }

    public BookRating(Long id, Long bookId, String userName, int rating, String comment) {
        this.id = id;
        this.bookId = bookId;
        this.userName = userName;
        this.rating = rating;
        this.comment = comment;
    }    

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BookRating {" + "bookId=" + bookId + ", userId=" + userName + ", rating=" + rating + ", "
                + "comment=" + comment + '}';
    }
    
}