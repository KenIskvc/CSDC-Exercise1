package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void testLoadMovies() {
        ObservableList<Movie> movies = homeController.getObservableMovies();
        assertEquals(4, movies.size(), "Die Anzahl der geladenen Filme ist falsch");
    }

//    @Test
//    void testSortingByTitleAscending() {
//        homeController.setSorting("Title", true);
//        homeController.sortMovies();
//        ObservableList<Movie> movies = homeController.getObservableMovies();
//
//        assertEquals("Forrest Gump", movies.get(0).getTitle());
//        assertEquals("Inception", movies.get(1).getTitle());
//        assertEquals("The Matrix", movies.get(2).getTitle());
//        assertEquals("The Social Network", movies.get(3).getTitle());
//    }
//
//    @Test
//    void testSortingByRatingDescending() {
//        homeController.setSorting("Rating", false);
//        ObservableList<Movie> movies = homeController.getObservableMovies();
//
//        assertEquals("Inception", movies.get(0).getTitle());
//        assertEquals("Forrest Gump", movies.get(1).getTitle());
//        assertEquals("The Matrix", movies.get(2).getTitle());
//        assertEquals("The Social Network", movies.get(3).getTitle());
//    }
//
//    @Test
//    void testSearchFunctionality() {
//        homeController.searchField = new javafx.scene.control.TextField();
//        homeController.searchField.setText("matrix");
//        homeController.filterMovies();
//        ObservableList<Movie> filteredMovies = homeController.getObservableMovies();
//
//        assertEquals(1, filteredMovies.size(), "Nur 'The Matrix' sollte gefunden werden");
//        assertEquals("The Matrix", filteredMovies.get(0).getTitle());
//    }
}
