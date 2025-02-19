package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    @Test
    void getGenres_shouldReturnAllGenres() {
        // Arrange
        List<Genre> expectedGenres = List.of(Genre.values());

        // Act
        List<Genre> actualGenres = Genre.getGenres();

        // Assert
        assertEquals(expectedGenres.size(), actualGenres.size(), "Die Anzahl der Genres stimmt nicht überein.");
        assertTrue(actualGenres.containsAll(expectedGenres), "Die Liste enthält nicht alle erwarteten Genres.");
    }

    @Test
    void getGenres_shouldHaveSameOrderAsValues() {
        // Act
        List<Genre> genres = Genre.getGenres();

        // Assert
        assertArrayEquals(Genre.values(), genres.toArray(), "Die Reihenfolge der Genres ist nicht korrekt.");
    }

    @Test
    void getGenres_shouldReturnModifiableList() {
        // Act
        List<Genre> genres = Genre.getGenres();

        // Assert
        assertDoesNotThrow(() -> genres.add(Genre.ACTION), "Die Liste sollte modifizierbar sein.");
    }
}
