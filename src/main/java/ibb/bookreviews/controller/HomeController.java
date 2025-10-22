package ibb.bookreviews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Leitet von der Startseite "/" automatisch auf die Bücherliste weiter.
 *      http://localhost:8080  ->  http://localhost:8080/books
 * Benutzer muss sich nicht /books merken.
 * @author Simone Njike
 * @date 21.10.2025
 */
@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        
        return "redirect:/books";
    }
}