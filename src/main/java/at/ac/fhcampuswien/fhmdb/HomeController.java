package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<String> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // Genres zur ComboBox hinzufügen
        genreComboBox.getItems().add("Alle Genres"); // Option zum Zurücksetzen
        for (Genre genre : Genre.values()) {
            genreComboBox.getItems().add(genre.name());
        }

        // Event für Genre-Auswahl hinzufügen
        genreComboBox.setOnAction(event -> filterByGenre());
        genreComboBox.setPromptText("Filter by Genre");

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });


    }
    private void filterByGenre() {
        String selectedGenre = (String) genreComboBox.getValue();

        if (selectedGenre == null || selectedGenre.equals("Alle Genres")) {
            observableMovies.setAll(allMovies); // Kein Filter -> Alle Filme anzeigen
        } else {
            Genre genreFilter = Genre.valueOf(selectedGenre);
            observableMovies.setAll(
                    allMovies.stream()
                            .filter(movie -> movie.getGenres().contains(genreFilter))
                            .toList()
            );
        }
    }
}