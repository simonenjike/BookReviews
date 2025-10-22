# 📚 BookReviews – Spring Boot Projekt

### 📖 Aufgabe: Buchbewertungen verwalten

Dies ist ein vollständiges **Spring Boot 3**-Projekt, das im Rahmen der **5. Wochenaufgabe (IBB)** entwickelt wurde.  
Die Anwendung ermöglicht es Benutzern, Bücher anzuzeigen und Bewertungen hinzuzufügen.  
Sie demonstriert die Verwendung von **Spring Web MVC**, **Thymeleaf**, **CDI (Dependency Injection)**, **SLF4J Logging**  
und **Java Faker** zur Generierung von Testdaten.

---

## 🧱 Projektstruktur

```bash
bookreviews/
├── src/main/java/ibb/bookreviews
│   ├── BookReviewsApplication.java        # Startklasse mit @SpringBootApplication
│   ├── config/AppConfig.java              # Definiert zusätzliche Beans (z. B. Faker)
│   ├── controller/
│   │   ├── HomeController.java            # Leitet "/" -> "/books"
│   │   ├── BookController.java            # Steuert Bücher- und Bewertungsseiten
│   │   └── GlobalExceptionHandler.java    # Zentrale Fehlerbehandlung
│   ├── model/
│   │   ├── Book.java                      # Buchdatenmodell
│   │   └── BookRating.java                # Bewertungsmodell
│   ├── service/
│   │   ├── BookService.java               # Erzeugt Fake-Bücher (ApplicationScope)
│   │   └── BookRatingService.java         # Verwaltet Bewertungen (SessionScope)
│   └── SessionUserData.java               # (optional) Session-spezifische Nutzerdaten
│
├── src/main/resources/
│   ├── templates/
│   │   ├── book-list.html                 # Bücherliste mit Details-Link
│   │   ├── book-details.html              # Buchdetails + Bewertungsformular
│   │   └── error.html                     # Benutzerfreundliche Fehlerseite
│   ├── application.properties             # Projektkonfiguration
│   └── logback-spring.xml                 # Logging-Konfiguration
│
├── pom.xml                                # Maven-Konfiguration
└── README.md                              # Projektdokumentation
```

---

## 🚀 Starten der Anwendung

### Voraussetzung:
- **Java 17 oder höher**
- **Maven 3.8+**

### Starten über Konsole:
```bash
mvn spring-boot:run
```

oder in NetBeans:
> Rechtsklick → Run Project

### Zugriff:
Öffne im Browser:  
👉 [http://localhost:8080](http://localhost:8080)

---

## 🧩 Funktionen

| Funktion | Beschreibung |
|-----------|---------------|
| 📚 **Bücher anzeigen** | Liste aller Bücher mit Faker-generierten Daten |
| 🔍 **Details anzeigen** | Titel, Autor, ISBN, Genre, Jahr |
| 📝 **Bewertungen hinzufügen** | Formular mit Benutzername, Bewertung (1–5) und Kommentar |
| 💬 **Bewertungen anzeigen** | Alle Bewertungen pro Buch |
| ⚠️ **Validierung** | Fehlermeldung, wenn Kommentar leer |
| 💡 **Flash-Meldungen** | Erfolg/Fehler nach Formularabsenden |
| 🧠 **CDI-Konfiguration** | @Service, @Controller, @Bean, @Autowired, @SessionScope |
| 📜 **Logging** | SLF4J + Logback (info, warn, error) |
| 🧯 **Fehlerbehandlung** | GlobalExceptionHandler + eigene Fehlerseite |

---

## 🧰 Technologien & Tools

| Technologie | Beschreibung |
|--------------|--------------|
| ☕ **Java 17** | Programmiersprache |
| 🌱 **Spring Boot 3.5.6** | Framework für Web & DI |
| 🧩 **Thymeleaf** | Template Engine für HTML |
| 🧠 **CDI / Dependency Injection** | @Service, @Controller, @Bean, @Autowired |
| 🎭 **Java Faker** | Generiert zufällige Testdaten |
| 🪵 **SLF4J + Logback** | Logging-Framework |
| 🧪 **Spring Boot DevTools** | Live-Reload bei Entwicklung |

---

## 📁 Beispiel-Daten (durch Faker generiert)

| ID | Titel | Autor | Genre | Jahr | ISBN |
|----|--------|--------|--------|------|------|
| 1 | The Silent Forest | John Doe | Drama | 1998 | 978-1-234567-89-7 |
| 2 | Dreaming Tomorrow | Jane Smith | Sci-Fi | 2007 | 978-3-456789-01-2 |
| … | … | … | … | … | … |

---

## 🧩 Codebeispiele

### Bewertung hinzufügen (`BookController.java`)
```java
@PostMapping("/{id}/addRating")
public String addRating(@PathVariable Long id,
                        @ModelAttribute("newRating") BookRating rating,
                        RedirectAttributes redirectAttributes) {

    if (rating.getComment() == null || rating.getComment().trim().isEmpty()) {
        log.warn("⚠️ Benutzer '{}' hat versucht, Bewertung ohne Kommentar für Buch {} zu speichern.",
                rating.getUserName(), id);
        redirectAttributes.addFlashAttribute("error", "Bitte geben Sie einen Kommentar ein!");
        return "redirect:/books/" + id;
    }

    rating.setBookId(id);
    ratingService.addRating(rating);
    log.info("✅ Neue Bewertung gespeichert: Buch-ID={}, Benutzer={}, Bewertung={}, Kommentar={}",
            id, rating.getUserName(), rating.getRating(), rating.getComment());

    redirectAttributes.addFlashAttribute("success", "Bewertung erfolgreich gespeichert!");
    return "redirect:/books/" + id;
}
```

---

## 🧠 Architekturüberblick

```text
Browser  ⇄  Controller  ⇄  Service  ⇄  Model
   │           │             │
   │           │             └── Book & BookRating
   │           └── BookController, HomeController
   └── Thymeleaf Templates (book-list.html, book-details.html)
```

---

## 🧱 CDI-Beziehungen (Dependency Injection)

| Quelle | Ziel | Annotation |
|---------|------|-------------|
| `BookController` | `BookService` | `@Autowired` |
| `BookController` | `BookRatingService` | `@Autowired` |
| `BookService` | `Faker` | `@Autowired` (`@Bean` in `AppConfig`) |

---

## 🪵 Beispiel-Logausgabe

```
12:45:23.152 INFO  BookService - 📚 10 Bücher wurden mit Java Faker generiert.
12:46:01.684 INFO  BookController - 🔍 Buchdetails für Buch-ID 1 werden geladen …
12:46:17.332 WARN  BookController - ⚠️ Benutzer 'Simone' hat versucht, Bewertung ohne Kommentar für Buch-ID 1 zu speichern.
12:46:25.911 INFO  BookController - ✅ Neue Bewertung gespeichert: Buch-ID=1, Benutzer=Simone, Bewertung=5, Kommentar='Sehr gutes Buch!'
```

---

## 👩‍💻 Autorin

**Name:** Simone Njike  
**Kurs:** IBB – Java Grundlagen und Spring Boot  
**Datum:** 21. Oktober 2025  

---

## 🏁 Fazit

Dieses Projekt zeigt ein voll funktionsfähiges **Spring Boot Web- und MVC-System**,  
das die wichtigsten Technologien einer modernen Java-Anwendung vereint:  
- saubere Schichtenarchitektur  
- CDI-Konfiguration  
- Logging  
- Fehlerbehandlung  
- und eine ansprechende Thymeleaf-Oberfläche.  

> ✅ **Alle Aufgabenpunkte (1–8) wurden vollständig erfüllt und erweitert.**
