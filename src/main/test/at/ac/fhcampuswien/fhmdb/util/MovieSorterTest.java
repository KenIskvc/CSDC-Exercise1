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
        movies.add(new Movie("Forrest Gump", "A simple man’s life journey.", List.of(Genre.DRAMA, Genre.ROMANCE), 1994, 8.8));
        movies.add(new Movie("Braveheart", "Scottish warrior William Wallace fights for freedom.", List.of(Genre.DRAMA, Genre.WAR, Genre.HISTORY), 1995, 8.3));
        movies.add(new Movie("The Social Network", "The rise of Facebook.", List.of(Genre.BIOGRAPHY, Genre.DRAMA), 2010, 7.7));
        movies.add(new Movie("Bohemian Rhapsody", "Freddie Mercury’s journey with Queen.", List.of(Genre.BIOGRAPHY, Genre.MUSIC, Genre.DRAMA), 2018, 8.0));
        movies.add(new Movie("Steins;Gate", "A scientist stumbles upon time travel.", List.of(Genre.ANIMATION, Genre.SCI_FI, Genre.MYSTERY), 2011, 9.1));
        movies.add(new Movie("Death Note", "A student gains power over life and death.", List.of(Genre.ANIMATION, Genre.MYSTERY, Genre.THRILLER), 2006, 9.0));
    }

    @Test
    void sortMoviesByTitleAscending_shouldSortAlphabetically() {
        MovieSorter.sortMovies(movies, MovieSorter.SortBy.TITLE, true);

        assertEquals("Bohemian Rhapsody", movies.get(0).getTitle());
        assertEquals("Braveheart", movies.get(1).getTitle());
        assertEquals("Death Note", movies.get(2).getTitle());
        assertEquals("Forrest Gump", movies.get(3).getTitle());
        assertEquals("Steins;Gate", movies.get(4).getTitle());
        assertEquals("The Social Network", movies.get(5).getTitle());
    }

    @Test
    void sortMoviesByTitleDescending_shouldSortInReverseOrder() {
        MovieSorter.sortMovies(movies, MovieSorter.SortBy.TITLE, false);

        assertEquals("The Social Network", movies.get(0).getTitle());
        assertEquals("Steins;Gate", movies.get(1).getTitle());
        assertEquals("Forrest Gump", movies.get(2).getTitle());
        assertEquals("Death Note", movies.get(3).getTitle());
        assertEquals("Braveheart", movies.get(4).getTitle());
        assertEquals("Bohemian Rhapsody", movies.get(5).getTitle());
    }

    @Test
    void sortMoviesByRatingDescending_shouldSortHighestRatingFirst() {
        MovieSorter.sortMovies(movies, MovieSorter.SortBy.RATING, false);

        assertEquals("Steins;Gate", movies.get(0).getTitle());  // 9.1
        assertEquals("Death Note", movies.get(1).getTitle());   // 9.0
        assertEquals("Forrest Gump", movies.get(2).getTitle());   // 8.8
        assertEquals("Braveheart", movies.get(3).getTitle());   // 8.3
        assertEquals("Bohemian Rhapsody", movies.get(4).getTitle());   // 8.0
        assertEquals("The Social Network", movies.get(5).getTitle());   // 7.7
    }

    @Test
    void sortMoviesByRatingAscending_shouldSortLowestRatingFirst() {
        MovieSorter.sortMovies(movies, MovieSorter.SortBy.RATING, true);

        assertEquals("The Social Network", movies.get(0).getTitle());  // 7.7
        assertEquals("Bohemian Rhapsody", movies.get(1).getTitle());   // 8.0
        assertEquals("Braveheart", movies.get(2).getTitle());   // 8.3
        assertEquals("Forrest Gump", movies.get(3).getTitle());   // 8.8
        assertEquals("Death Note", movies.get(4).getTitle());   // 9.0
        assertEquals("Steins;Gate", movies.get(5).getTitle());   // 9.1
    }

    @Test
    void sortMoviesByReleaseYearAscending_shouldSortOldestFirst() {
        MovieSorter.sortMovies(movies, MovieSorter.SortBy.RELEASE_YEAR, true);

        assertEquals("Forrest Gump", movies.get(0).getTitle());   // 1994
        assertEquals("Braveheart", movies.get(1).getTitle());   // 1995
        assertEquals("Death Note", movies.get(2).getTitle());   // 2006
        assertEquals("The Social Network", movies.get(3).getTitle());   // 2010
        assertEquals("Steins;Gate", movies.get(4).getTitle());   // 2011
        assertEquals("Bohemian Rhapsody", movies.get(5).getTitle());   // 2018
    }

    @Test
    void sortMoviesByReleaseYearDescending_shouldSortNewestFirst() {
        MovieSorter.sortMovies(movies, MovieSorter.SortBy.RELEASE_YEAR, false);

        assertEquals("Bohemian Rhapsody", movies.get(0).getTitle());  // 2018
        assertEquals("Steins;Gate", movies.get(1).getTitle());   // 2011
        assertEquals("The Social Network", movies.get(2).getTitle());   // 2010
        assertEquals("Death Note", movies.get(3).getTitle());   // 2006
        assertEquals("Braveheart", movies.get(4).getTitle());   // 1995
        assertEquals("Forrest Gump", movies.get(5).getTitle());   // 1994
    }
}
