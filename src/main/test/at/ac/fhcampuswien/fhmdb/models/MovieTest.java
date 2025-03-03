package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private List<Movie> movies;

    @BeforeEach
    void setUp() {
        movies = Movie.initializeMovies();
    }


    // Tester für die neue Movieklasse
    @Test
    void testInitializeMovies_size() {
        List<Movie> movies = Movie.initializeMovies();
        assertNotNull(movies, "Die Movie-Liste sollte nicht null sein.");
        assertEquals(35, movies.size(), "Die Movie-Liste sollte genau 35 Filme enthalten.");
    }

    @Test
    void testInitializeMovies_movieFields() {
        List<Movie> movies = Movie.initializeMovies();
        assertFalse(movies.isEmpty(), "Die Movie-Liste sollte nicht leer sein.");

        Movie movie = movies.get(0);

        assertNotNull(movie.getTitle(), "Der Titel darf nicht null sein.");
        assertFalse(movie.getTitle().isEmpty(), "Der Titel darf nicht leer sein.");

        assertNotNull(movie.getDescription(), "Die Beschreibung darf nicht null sein.");
        assertFalse(movie.getDescription().isEmpty(), "Die Beschreibung darf nicht leer sein.");

        assertNotNull(movie.getGenres(), "Genres dürfen nicht null sein.");
        assertTrue(movie.getGenres().size() >= 1 && movie.getGenres().size() <= 3, "Ein Film sollte 1 bis 3 Genres haben.");

        assertTrue(movie.getReleaseYear() >= 1980 && movie.getReleaseYear() <= 2025, "Das Erscheinungsjahr sollte zwischen 1980 und 2025 liegen.");

        assertTrue(movie.getRating() >= 4.0 && movie.getRating() <= 10.0, "Das Rating sollte zwischen 4.0 und 10.0 liegen.");
    }

    @Test
    void testGetRandomGenres_size() {
        int requestedSize = 2;
        List<Genre> genres = Movie.getRandomGenres(requestedSize);
        assertNotNull(genres, "Die Genre-Liste sollte nicht null sein.");
        assertEquals(requestedSize, genres.size(), "Die Genre-Liste sollte genau " + requestedSize + " Genres enthalten.");
    }

    @Test
    void testGetRandomGenres_noDuplicates() {
        int requestedSize = 5;

        // Falls Genre.size < requested size
        List<Genre> allGenres = Genre.getGenres();
        int maxSize = Math.min(requestedSize, allGenres.size());

        List<Genre> randomGenres = Movie.getRandomGenres(maxSize);

        assertEquals(maxSize, randomGenres.size(), "Die Größe sollte übereinstimmen.");
        assertEquals(randomGenres.stream().distinct().count(), randomGenres.size(), "Die Genres sollten einzigartig sein.");
    }
}


