package ibb.bookreviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Die Klasse startet die gesamte Anwendung.
 * @author Simone Njike
 * @date 21.10.2025
 */
@SpringBootApplication
public class BookReviewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookReviewsApplication.class, args); 
    }
    
    /**
     * @SpringBootApplication ist ein Meta-Annotation, die 3 andere Dinge aktiviert:
     *          @Configuration – ermöglicht eigene Beans.
     *          @EnableAutoConfiguration – Spring konfiguriert Web, Logging usw. automatisch.
     *          @ComponentScan – Spring durchsucht das Paket ibb.bookreviews nach @Controller, 
     *                                          @Service, @Component …
     */

}