package ibb.bookreviews.web;

import jakarta.annotation.PostConstruct;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Die Klasse speichert Benutzerdaten nur für die aktuelle Sitzung (Session).
 * @author Simone Njike
 * @date 21.10.2025
 */
@Component
@SessionScope
public class SessionUserData {
    
    private String username;

    // z. B. beim ersten Besuch einen zufälligen Namen setzen
    @PostConstruct
    public void init() {
        
        this.username = "User" + new Random().nextInt(1000);
    }

    public String getUsername() {
        return username;
    }    
    
}