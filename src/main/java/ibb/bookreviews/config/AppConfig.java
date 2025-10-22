package ibb.bookreviews.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Konfigurationsklasse, die zusätzliche Beans definiert.
 * Spring erkennt diese automatisch dank @Configuration.
 * @author Simone Njike
 * @date 22.10.2025
 */
@Configuration          // markiert diese Klasse als CDI-Konfigurationsklasse.
public class AppConfig {

    /**
     * Erstellt eine Faker-Bean, die in anderen Klassen mit @Autowired benutzt werden kann.
     * @Bean registriert das zurückgegebene Objekt (Faker) als Spring-Bean.
     * Diese Bean kann dann mit @Autowired in beliebige andere Klassen injiziert werden.
     */
    @Bean
    public Faker faker() {
        return new Faker();
    }
    
}