package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.stream.Collectors;

public class MovieCell extends ListCell<Movie> {

    private final VBox layout = new VBox();
    private final HBox titleBox = new HBox();
    private final Label titleLabel = new Label();
    private final Label yearLabel = new Label();
    private final Label genreLabel = new Label();
    private final Label ratingLabel = new Label();
    private final Label descriptionLabel = new Label();

    public MovieCell() {
        titleLabel.setFont(Font.font("Arial", 18));
        titleLabel.setTextFill(Color.web("#FFD700")); // Gold

        yearLabel.setFont(Font.font("Arial", 14));
        yearLabel.setTextFill(Color.web("#CCCCCC")); // Grau
        yearLabel.setStyle("-fx-padding: 0 0 0 10px;");

        genreLabel.setFont(Font.font("Arial", 14));
        genreLabel.setTextFill(Color.web("#4DB8FF"));

        ratingLabel.setFont(Font.font("Arial", 16));
        ratingLabel.setTextFill(Color.web("#FFA500"));

        descriptionLabel.setFont(Font.font("Arial", 14));
        descriptionLabel.setTextFill(Color.web("#DDDDDD"));
        descriptionLabel.setWrapText(true);

        titleBox.getChildren().addAll(titleLabel, yearLabel);
        layout.getChildren().addAll(titleBox, genreLabel, ratingLabel, descriptionLabel);

        // Standard-Styling der MovieCell
        layout.setStyle("-fx-background-color: #333333; " +
                "-fx-padding: 15; " +
                "-fx-background-radius: 10; " +
                "-fx-border-color: #666666; " +
                "-fx-border-radius: 10;");
        layout.setSpacing(10);
        layout.setMinHeight(100); // Fixierte Höhe, um Größenänderungen zu vermeiden

        // **Hover-Effekt nur Rand und Hintergrundfarbe ändern (KEINE Größe)**
        setOnMouseEntered(event -> layout.setStyle("-fx-background-color: #444444; " +
                "-fx-border-color: #FFD700; " +
                "-fx-background-radius: 10; " +
                "-fx-border-radius: 10; " +
                "-fx-padding: 15;"));
        setOnMouseExited(event -> layout.setStyle("-fx-background-color: #333333; " +
                "-fx-border-color: #666666; " +
                "-fx-background-radius: 10; " +
                "-fx-border-radius: 10; " +
                "-fx-padding: 15;"));
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText(movie.getTitle());
            yearLabel.setText("(" + movie.getReleaseYear() + ")");
            genreLabel.setText("Genres: " + movie.getGenres().stream().map(Enum::name).collect(Collectors.joining(", ")));
            ratingLabel.setText("⭐ " + String.format("%.1f", movie.getRating()));
            descriptionLabel.setText(movie.getDescription());

            setGraphic(layout);
        }
    }
}