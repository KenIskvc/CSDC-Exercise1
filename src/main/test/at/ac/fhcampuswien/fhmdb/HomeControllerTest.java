package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.jfoenix.controls.JFXComboBox;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
class HomeControllerTest {

    private HomeController homeController;
    private List<Movie> testMovies;

    @BeforeEach
    void setUp() {
        homeController = new HomeController();
        homeController.disableAnimationForTests(); // ⚡ Keine Animation im Test

        testMovies = List.of(
                new Movie("Inception", "Dream heist", List.of(Genre.ACTION, Genre.SCI_FI, Genre.THRILLER), 2010, 8.8),
                new Movie("Forrest Gump", "Life story", List.of(Genre.DRAMA, Genre.ROMANCE), 1994, 8.8),
                new Movie("The Matrix", "Virtual reality revealed", List.of(Genre.ACTION, Genre.SCI_FI), 1999, 8.7),
                new Movie("The Social Network", "The story of Facebook", List.of(Genre.BIOGRAPHY, Genre.DRAMA), 2010, 7.7)
        );

        homeController.loadMovies(testMovies);
        homeController.initializeMovies();
        homeController.genreComboBox = mock();
        homeController.sortComboBox = mock();
        homeController.searchField = mock(TextField.class);
    }

    @Test
    void testLoadMovies() {
        ObservableList<Movie> movies = homeController.getObservableMovies();
        assertEquals(4, movies.size(), "Die Anzahl der geladenen Filme ist falsch");
        System.out.println("testLoadMovies() succeeded.");
    }

    @Test
    void testSortingByTitleAscending() {
        homeController.setAscending(true);
        when(homeController.sortComboBox.getValue()).thenReturn("Title");
        homeController.sortMovies();
        ObservableList<Movie> movies = homeController.getObservableMovies();

        assertEquals("Forrest Gump", movies.get(0).getTitle());
        assertEquals("Inception", movies.get(1).getTitle());
        assertEquals("The Matrix", movies.get(2).getTitle());
        assertEquals("The Social Network", movies.get(3).getTitle());
    }

    @Test
    void testSortingByTitleDescending() {
        homeController.setAscending(false);
        when(homeController.sortComboBox.getValue()).thenReturn("Title");
        homeController.sortMovies();
        ObservableList<Movie> movies = homeController.getObservableMovies();

        assertEquals("Forrest Gump", movies.get(3).getTitle());
        assertEquals("Inception", movies.get(2).getTitle());
        assertEquals("The Matrix", movies.get(1).getTitle());
        assertEquals("The Social Network", movies.get(0).getTitle());
    }

    @Test
    void testSortingByRatingDescending() {
        when(homeController.sortComboBox.getValue()).thenReturn("Rating");
        homeController.sortMovies();
        ObservableList<Movie> movies = homeController.getObservableMovies();
        assertEquals("Inception", movies.get(0).getTitle());
        assertEquals("Forrest Gump", movies.get(1).getTitle());
        assertEquals("The Matrix", movies.get(2).getTitle());
        assertEquals("The Social Network", movies.get(3).getTitle());
    }

    @Test
    void testSortingByReleaseYearAscending() {
        homeController.setAscending(true);
        when(homeController.sortComboBox.getValue()).thenReturn("Release Year");
        homeController.sortMovies();
        ObservableList<Movie> movies = homeController.getObservableMovies();
        assertEquals("Forrest Gump", movies.get(0).getTitle());
        assertEquals("The Matrix", movies.get(1).getTitle());
    }

    /**
     * Searching for a specific movie title.
     */
    @Test
    void testSearchFunctionality() {
        when(homeController.searchField.getText()).thenReturn("Forrest Gump");
        homeController.filterMovies();
        ObservableList<Movie> filteredMovies = homeController.getObservableMovies();

        assertEquals(1, filteredMovies.size(), "Nur 'Forrest Gump' sollte gefunden werden");
        assertEquals("Forrest Gump", filteredMovies.get(0).getTitle());
    }

    /**
     * Searching for a specific movie description.
     */
    @Test
    void testSearchFunctionality2() {
        when(homeController.searchField.getText()).thenReturn("Dream heist");
        homeController.filterMovies();
        ObservableList<Movie> filteredMovies = homeController.getObservableMovies();

        assertEquals(1, filteredMovies.size(), "Nur 'Inception' sollte gefunden werden");
        assertEquals("Inception", filteredMovies.get(0).getTitle());
    }

    @Test
    void testFilterByGenre() {
        when(homeController.genreComboBox.getValue()).thenReturn("DRAMA");
        homeController.filterMovies();
        ObservableList<Movie> filteredMovies = homeController.getObservableMovies();

        assertEquals(2, filteredMovies.size());
        assertEquals("Forrest Gump", filteredMovies.get(0).getTitle());
        assertEquals("The Social Network", filteredMovies.get(1).getTitle());
    }
}
