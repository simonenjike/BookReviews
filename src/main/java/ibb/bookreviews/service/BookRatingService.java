package ibb.bookreviews.service;

import ibb.bookreviews.model.BookRating;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Diese Serviceklasse verwaltet alle Benutzerbewertungen im SessionScope.
 *      D.h.  jede Browsersitzung speichert ihre Bewertungen bzw.
 *              Jede Benutzersession bekommt ihre eigene Instanz.
 * @author Simone Njike
 * @date 21.10.2025
 */
@Service             // <--- CDI
@SessionScope   // <--- CDI-Scope (jede Session bekommt eigene Bean)
public class BookRatingService {
    
    private static final Logger log = LoggerFactory.getLogger(BookRatingService.class);
    private List<BookRating> ratings = new ArrayList<>();       // einfache Liste (kein DB nötig)
    
    /**
     * Gibt alle Bewertungen für ein bestimmtes Buch zurück.
     * @param bookId
     * @return 
     */
    public List<BookRating> getRatingsForBook(Long bookId) {
        
        log.trace("Bewertungen für Buch {} werden geladen", bookId);
        
        // Filter mit stream() zeigt nur Bewertungen für das ausgewählte Buch.
        return ratings.stream().filter(r -> r.getBookId().equals(bookId)).collect(Collectors.toList());
    }
    
    /**
     * Fügt eine neue Bewertung hinzu.
     * @param rating 
     */
    public void addRating(BookRating rating) {
        
        rating.setId( (long) (ratings.size() + 1) );    // einfache ID-Vergabe
        ratings.add(rating);
        
        log.debug("Neue Bewertung hinzugefügt (BookId={}, Benutzer={})", 
                rating.getBookId(), rating.getUserName());
    }
    
    /**
     * Gibt alle Bewertungen zurück (optional für Debugging).
     * @return 
     */
    public List<BookRating> getAllRatings() {
        
        return ratings;
    }
    
}