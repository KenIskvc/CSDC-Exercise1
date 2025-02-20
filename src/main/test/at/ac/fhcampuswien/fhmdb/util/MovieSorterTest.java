package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieSorterTest {

    private List<Movie> movies;

    @BeforeEach
    void setUp() {
        movies = new ArrayList<>();
        movies.add(new Movie("Interstellar", "Sci-fi space travel", List.of(Genre.SCI_FI, Genre.DRAMA)));
        movies.add(new Movie("The Godfather", "Mafia crime drama", List.of(Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("Forrest Gump", "Life story of a simple man", List.of(Genre.DRAMA, Genre.ROMANCE)));
    }

    @Test
    void sortMoviesAscending_shouldSortAlphabetically() {
        MovieSorter.sortMovies(movies, true);

        assertEquals("Forrest Gump", movies.get(0).getTitle());
        assertEquals("Interstellar", movies.get(1).getTitle());
        assertEquals("The Godfather", movies.get(2).getTitle());
    }

    @Test
    void sortMoviesDescending_shouldSortInReverseOrder() {
        MovieSorter.sortMovies(movies, false);

        assertEquals("The Godfather", movies.get(0).getTitle());
        assertEquals("Interstellar", movies.get(1).getTitle());
        assertEquals("Forrest Gump", movies.get(2).getTitle());
    }
}
