package ibb.bookreviews.controller;

import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Fängt globale Fehler ab und zeigt eine benutzerfreundliche Seite (error.html).
 * @author Simone Njike
 * @date 22.10.2025
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * Behandelt Fälle, in denen ein Buch nicht gefunden wird.
     * @param ex
     * @param model
     * @return 
     */
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElement(NoSuchElementException ex, Model model) {
        
        log.error("❌ Fehler: {}", ex.getMessage());
        model.addAttribute("message", "Das angeforderte Buch wurde nicht gefunden.");
        
        return "error";     // templates/error.html
    }

    /**
     * Fängt alle anderen unerwarteten Fehler ab.
     * @param ex
     * @param model
     * @return 
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        
        log.error("❌ Unerwarteter Fehler: {}", ex.getMessage(), ex);
        model.addAttribute("message", "Ein unerwarteter Fehler ist aufgetreten!");
        return "error";
    }
    
}