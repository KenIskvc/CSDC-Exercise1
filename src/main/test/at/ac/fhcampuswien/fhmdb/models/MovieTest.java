package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private List<Movie> movies;

    @BeforeEach
    void setUp() {
        movies = Movie.loadMoviesFromXml();
    }

    @Test
    void movieConstructor_shouldInitializeFieldsCorrectly() {
        // Arrange
        String expectedTitle = "Inception";
        String expectedDescription = "A thief who enters dreams to steal secrets must plant an idea.";
        List<Genre> expectedGenres = List.of(Genre.ACTION, Genre.SCI_FI, Genre.THRILLER);
        int expectedYear = 2010;
        double expectedRating = 8.8;

        // Act
        Movie movie = new Movie(expectedTitle, expectedDescription, expectedGenres, expectedYear, expectedRating);

        // Assert
        assertEquals(expectedTitle, movie.getTitle(), "Der Titel stimmt nicht überein.");
        assertEquals(expectedDescription, movie.getDescription(), "Die Beschreibung stimmt nicht überein.");
        assertEquals(expectedGenres, movie.getGenres(), "Die Genres stimmen nicht überein.");
        assertEquals(expectedYear, movie.getReleaseYear(), "Das Erscheinungsjahr stimmt nicht überein.");
        assertEquals(expectedRating, movie.getRating(), 0.1, "Die Bewertung stimmt nicht überein.");
    }

    @Test
    void getTitle_shouldReturnCorrectTitle() {
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", List.of(Genre.ROMANCE, Genre.DRAMA), 1997, 7.8);
        assertEquals("Titanic", movie.getTitle(), "Der Titel ist nicht korrekt.");
    }

    @Test
    void getDescription_shouldReturnCorrectDescription() {
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", List.of(Genre.ROMANCE, Genre.DRAMA), 1997, 7.8);
        assertEquals("A love story on the Titanic.", movie.getDescription(), "Die Beschreibung ist nicht korrekt.");
    }

    @Test
    void getGenres_shouldReturnCorrectGenres() {
        List<Genre> expectedGenres = List.of(Genre.ROMANCE, Genre.DRAMA);
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", expectedGenres, 1997, 7.8);
        assertEquals(expectedGenres, movie.getGenres(), "Die Genres sind nicht korrekt.");
    }

    @Test
    void getReleaseYear_shouldReturnCorrectYear() {
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", List.of(Genre.ROMANCE, Genre.DRAMA), 1997, 7.8);
        assertEquals(1997, movie.getReleaseYear(), "Das Erscheinungsjahr ist nicht korrekt.");
    }

    @Test
    void getRating_shouldReturnCorrectRating() {
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", List.of(Genre.ROMANCE, Genre.DRAMA), 1997, 7.8);
        assertEquals(7.8, movie.getRating(), 0.1, "Die Bewertung ist nicht korrekt.");
    }

    @Test
    void loadMoviesFromXml_shouldReturnNonEmptyList() {
        assertNotNull(movies, "Die Liste darf nicht null sein.");
        assertFalse(movies.isEmpty(), "Die Liste sollte nicht leer sein.");
    }

    @Test
    void loadMoviesFromXml_shouldContainSpecificMovie() {
        boolean containsInception = movies.stream()
                .anyMatch(m -> m.getTitle().equals("Demon Slayer: Mugen Train") && m.getGenres().contains(Genre.ANIMATION));

        assertTrue(containsInception, "Die Liste sollte 'Demon Slayer: Mugen Train' mit dem Genre ANIMATION enthalten.");
    }

    @Test
    void loadMoviesFromXml_shouldContainAllGenres() {
        for (Genre genre : Genre.values()) {
            boolean containsGenre = movies.stream().anyMatch(m -> m.getGenres().contains(genre));
            assertTrue(containsGenre, "Es fehlt ein Film mit dem Genre: " + genre);
        }
    }
}
