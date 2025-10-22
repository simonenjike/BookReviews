package ibb.bookreviews.controller;

import ibb.bookreviews.model.BookRating;
import ibb.bookreviews.service.BookRatingService;
import ibb.bookreviews.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller für das Anzeigen der Bücherliste und Bewertungen.
 * Nutzt BookService, um die im Speicher vorhandenen Bücher anzuzeigen.
 * Steuert alle Seiten rund um Bücher und Bewertungen:
 *          Liste anzeigen / Bücherdetails + Bewertungen anzeigen / Neue Bewertung speichern
 * @author Simone Njike
 * @date 21.10.2025
 */
@Controller
@RequestMapping("/books")
public class BookController {
    
    // Logger-Objekt für diese Klasse
    private static final Logger log = LoggerFactory.getLogger(BookController.class);    
    
    @Autowired                                               //Objekt (Bean) automatisch bereitgestellt, ohne new 
    private BookService bookService;                // liefert Buchdaten
    
    @Autowired
    private BookRatingService ratingService;    // verwaltet Bewertungen (SessionScope)
    
    /**
     * GET /books - Zeigt alle Bücher (Template: book-list.html).
     * @param model
     * @return src/main/resources/templates/book-list.html
     */
    @GetMapping
    public String showBookList(Model model) {
                
        log.info("📚 Bücherliste wird geladen …");
        model.addAttribute("books", bookService.getAllBooks());
        
        return "book-list";
    }
    
    /**
     * GET /books/{id} 
     * Zeigt Buchdetails + Bewertungen (Template: book-details.html).
     * @param id
     * @param model
     * @return 
     */
    @GetMapping("/{id}")
    public String showBook(@PathVariable Long id, Model model) {
        
        log.info("🔍 Buchdetails für Buch-ID {} werden geladen …", id);        
        model.addAttribute("book", bookService.getBookById(id));                   // einzelnes Buch
        model.addAttribute("ratings", ratingService.getRatingsForBook(id));    // Bewertungen
        model.addAttribute("newRating", new BookRating());                          // Formular-Objekt
        
        return "book-details";  // neues Template
    }
    
    /**
     * POST /books/{id}/addRating – Bewertungsformular verarbeiten
     * Nimmt Formularwerte entgegen, prüft Kommentar und speichert Bewertung.
     * Nutzt Flash-Attribute für Benutzer-Meldungen nach dem Redirect.
     * @ModelAttribute("newRating") bindet Formularfelder an dein BookRating-Objekt
     * @param id
     * @param rating
     * @return 
     */
    @PostMapping("/{id}/addRating")        
    public String addRating(@PathVariable Long id, @ModelAttribute("newRating") BookRating rating, 
            RedirectAttributes redirectAttributes) {
        
        // Validierung: Kommentar muss vorhanden sein / Fehlerprüfung
        if (rating.getComment() == null || rating.getComment().trim().isEmpty()) {
            
            log.warn("⚠️ Benutzer '{}' hat versucht, Bewertung ohne Kommentar für Buch-ID {} zu speichern.",
                    rating.getUserName(), id);
            redirectAttributes.addFlashAttribute("error", "Bitte geben Sie einen Kommentar ein!");
            
            return "redirect:/books/" + id;
        }
        
        // Bewertung hinzufügen (wenn Kommentar vorhanden)
        rating.setBookId(id);
        ratingService.addRating(rating);
        
        log.info("✅ Neue Bewertung gespeichert: Buch-ID={}, Benutzer={}, Bewertung={}, Kommentar={}",
                id, rating.getUserName(), rating.getRating(), rating.getComment());
        
        // Erfolgsmeldung setzen /Thymeleaf zeigt die Nachricht nach dem Redirect
        redirectAttributes.addFlashAttribute("success", "Bewertung erfolgreich gespeichert!");
        
        // redirect: Seite neu laden (zeigt Buchdetails + neue Bewertung)
        return "redirect:/books/" + id;
    }    
    
    /**
     * GET /books/error – Fehlerseite anzeigen
     * @param model
     * @return 
     */
    @GetMapping("/error")
    public String error(Model model) {
    
        log.error("❌ Ein unerwarteter Fehler ist aufgetreten!");
        model.addAttribute("message", "Ein unerwarteter Fehler ist aufgetreten!");
    
        return "error";
    }
    
}