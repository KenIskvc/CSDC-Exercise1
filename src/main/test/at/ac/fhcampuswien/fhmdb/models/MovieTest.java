package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void movieConstructor_shouldInitializeFieldsCorrectly() {
        // Arrange
        String expectedTitle = "Inception";
        String expectedDescription = "A thief who enters the dreams of others to steal secrets must plant an idea into a target's subconscious.";
        List<Genre> expectedGenres = List.of(Genre.ACTION, Genre.SCI_FI, Genre.THRILLER);

        // Act
        Movie movie = new Movie(expectedTitle, expectedDescription, expectedGenres);

        // Assert
        assertEquals(expectedTitle, movie.getTitle(), "Der Titel stimmt nicht überein.");
        assertEquals(expectedDescription, movie.getDescription(), "Die Beschreibung stimmt nicht überein.");
        assertEquals(expectedGenres, movie.getGenres(), "Die Genres stimmen nicht überein.");
    }

    @Test
    void getTitle_shouldReturnCorrectTitle() {
        // Arrange
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", List.of(Genre.ROMANCE, Genre.DRAMA));

        // Act & Assert
        assertEquals("Titanic", movie.getTitle(), "Der Titel ist nicht korrekt.");
    }

    @Test
    void getDescription_shouldReturnCorrectDescription() {
        // Arrange
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", List.of(Genre.ROMANCE, Genre.DRAMA));

        // Act & Assert
        assertEquals("A love story on the Titanic.", movie.getDescription(), "Die Beschreibung ist nicht korrekt.");
    }

    @Test
    void getGenres_shouldReturnCorrectGenres() {
        // Arrange
        List<Genre> expectedGenres = List.of(Genre.ROMANCE, Genre.DRAMA);
        Movie movie = new Movie("Titanic", "A love story on the Titanic.", expectedGenres);

        // Act & Assert
        assertEquals(expectedGenres, movie.getGenres(), "Die Genres sind nicht korrekt.");
    }

    @Test
    void initializeMovies_shouldReturnNonEmptyList() {
        // Act
        List<Movie> movies = Movie.initializeMovies();

        // Assert
        assertNotNull(movies, "Wird verwendet, um sicherzustellen, dass eine Referenz auf ein Objekt existiert.");
        assertFalse(movies.isEmpty(), "Wird verwendet, um sicherzustellen, dass eine Collection mindestens ein Element enthält.");
    }

    @Test
    void initializeMovies_shouldContainSpecificMovie() {
        // Act
        List<Movie> movies = Movie.initializeMovies();
        boolean containsInception = movies.stream()
                .anyMatch(m -> m.getTitle().equals("Inception") && m.getGenres().contains(Genre.SCI_FI));

        // Assert
        assertTrue(containsInception, "Die Liste sollte 'Inception' mit dem Genre SCI_FI enthalten.");
    }
}
