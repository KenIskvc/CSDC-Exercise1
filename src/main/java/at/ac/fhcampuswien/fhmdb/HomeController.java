package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.util.MovieSorter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.application.Platform;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {

    @FXML
    public JFXComboBox<String> sortComboBox;
    @FXML
    public TextField searchField;
    @FXML
    public JFXListView<Movie> movieListView;
    @FXML
    public JFXComboBox<String> genreComboBox;
    @FXML
    public JFXButton sortBtn;

    private List<Movie> allMovies;
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    private boolean ascending = true;
    private boolean animationEnabled = true; // ⚡ Neue Option zum Deaktivieren der Animation für Tests

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMovies(Movie.loadMoviesFromXml());
        setupUI();
    }

    public void loadMovies(List<Movie> movies) {
        this.allMovies = movies;
        observableMovies.setAll(allMovies);
        if (movieListView != null) {
            movieListView.setItems(observableMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());
        }
    }

    private void setupUI() {
        if (genreComboBox != null) {
            genreComboBox.getItems().add("Alle Genres");
            for (Genre genre : Genre.values()) {
                genreComboBox.getItems().add(genre.name());
            }
            genreComboBox.setValue("Alle Genres");
            genreComboBox.setOnAction(event -> filterMovies());
        }

        if (sortComboBox != null) {
            sortComboBox.getItems().addAll("Title", "Rating", "Release Year");
            sortComboBox.setValue("Title");
            sortComboBox.setOnAction(event -> {
                sortMovies();
                if (animationEnabled) animateMovies();
            });
        }

        if (sortBtn != null) {
            sortBtn.setOnAction(event -> {
                ascending = !ascending;
                sortMovies();
                sortBtn.setText(ascending ? "Sort (Desc)" : "Sort (Asc)");
                if (animationEnabled) animateMovies();
            });
        }

        if (searchField != null) {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterMovies();
                if (animationEnabled) animateMovies();
            });
        }

        if (animationEnabled) animateMovies();
    }

    public void sortMovies() {
        if (sortComboBox == null) return;
        MovieSorter.SortBy selectedSort = switch (sortComboBox.getValue()) {
            case "Rating" -> MovieSorter.SortBy.RATING;
            case "Release Year" -> MovieSorter.SortBy.RELEASE_YEAR;
            default -> MovieSorter.SortBy.TITLE;
        };
        MovieSorter.sortMovies(observableMovies, selectedSort, ascending);
    }

    public void filterMovies() {
        if (genreComboBox == null || searchField == null) return;
        String selectedGenre = genreComboBox.getValue();
        String searchQuery = searchField.getText().trim().toLowerCase();

        List<Movie> filtered = allMovies.stream()
                .filter(movie -> selectedGenre.equals("Alle Genres") || movie.getGenres().contains(Genre.valueOf(selectedGenre)))
                .filter(movie -> searchQuery.isEmpty() ||
                        movie.getTitle().toLowerCase().contains(searchQuery) ||
                        movie.getDescription().toLowerCase().contains(searchQuery))
                .collect(Collectors.toList());

        observableMovies.setAll(filtered);
        sortMovies();
        if (animationEnabled) animateMovies();
    }

    public void animateMovies() {
        if (!animationEnabled || movieListView == null) return; // Keine Animation im Test

        Platform.runLater(() -> {
            List<Node> cells = movieListView.lookupAll(".list-cell").stream().toList();
            if (cells.isEmpty()) return;

            for (Node cell : cells) {
                if (cell != null) cell.setOpacity(0);
            }

            for (int i = 0; i < cells.size(); i++) {
                Node cell = cells.get(i);
                if (cell == null) continue;

                FadeTransition fade = new FadeTransition(Duration.millis(500), cell);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.setDelay(Duration.millis(i * 60));
                fade.play();
            }
        });
    }

    public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }

    public void setSorting(String sortBy, boolean ascending) {
        if (sortComboBox != null) {
            sortComboBox.setValue(sortBy);
        }
        this.ascending = ascending;
        sortMovies();
    }

    public void disableAnimationForTests() {
        this.animationEnabled = false;
    }
}
