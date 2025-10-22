package ibb.bookreviews.service;

import com.github.javafaker.Faker;
import ibb.bookreviews.model.Book;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Die Service-Klasse stellt Buchdaten bereit.
 * Erzeugt mit Java Faker einige Beispiel-Bücher beim Start.
 * @author Simone Njike
 * @date 21.10.2025
 */
@Service                 // <--- CDI: Spring soll diese Klasse verwalten
@ApplicationScope   // Nur eine Instanz für die gesamte Anwendung
public class BookService {
    
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    
    private final List<Book> books = new ArrayList<>(); // Liste mit allen generierten Büchern
    private final Random random = new Random();
    
    // Faker-Objekt : private final Faker faker = new Faker();
    
    // Faker wird jetzt per CDI injiziert – kommt aus AppConfig
    @Autowired
    private Faker faker;

    /**
     * Wird automatisch nach dem Erzeugen des Beans ausgeführt.
     * Initialisiert die Buchliste mit Fake-Daten.
     */
    @PostConstruct
    public void init() {
        generateFakeBooks(10); //10 Bücher werden generiert
        log.info("📚 {} Bücher wurden mit Java Faker generiert.", books.size());
    }

    /**
     * Faker erzeugt zufällige Fake-Daten (Titel, Autor, ISBN, ...)
     * @param count 
     */
    private void generateFakeBooks(int count) {
        
        for (long i = 1; i <= count; i++) {
            books.add(new Book(
                    i,
                    faker.book().title(),
                    faker.book().author(),
                    faker.code().isbn13(),
                    faker.book().genre(),
                    1980 + random.nextInt(45)
            ));
        }
    }

    /**
     * Liefert alle Bücher zurück.
     * @return 
     */
    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * findet ein bestimmtes Buch anhand seiner ID
     * Wirft Exception, falls nicht vorhanden.
     * @param id
     * @return 
     */
    public Book getBookById(Long id) {
        
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Buch mit ID " + id + " nicht gefunden."));
    }
    
}