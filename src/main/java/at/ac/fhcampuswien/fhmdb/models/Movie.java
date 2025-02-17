package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres=genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(
                "Forrest Gump",
                "The presidencies of Kennedy and Johnson, Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.",
                List.of(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie(
                "The Shawshank Redemption",
                "Two imprisoned men bond over several years, finding solace and eventual redemption through acts of common decency.",
                List.of(Genre.DRAMA)
        ));

        movies.add(new Movie(
                "Inception",
                "A thief who enters the dreams of others to steal secrets must plant an idea into a target's subconscious.",
                List.of(Genre.ACTION, Genre.SCI_FI, Genre.THRILLER)
        ));

        movies.add(new Movie(
                "The Godfather",
                "The aging patriarch of an organized crime dynasty transfers control to his reluctant son.",
                List.of(Genre.DRAMA, Genre.CRIME)
        ));

        movies.add(new Movie(
                "Titanic",
                "A young couple from different social classes fall in love aboard the ill-fated RMS Titanic.",
                List.of(Genre.ROMANCE, Genre.DRAMA)
        ));

        movies.add(new Movie(
                "The Dark Knight",
                "When the Joker emerges from Gotham's underworld, Batman must navigate moral dilemmas to stop him.",
                List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)
        ));
        movies.add(new Movie(
                "Pulp Fiction",
                "The lives of two hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                List.of(Genre.CRIME, Genre.DRAMA)
        ));

        movies.add(new Movie(
                "Interstellar",
                "A team of explorers travels through a wormhole in space in an attempt to ensure humanity's survival.",
                List.of(Genre.SCI_FI, Genre.DRAMA, Genre.ADVENTURE)
        ));

        movies.add(new Movie(
                "Schindler's List",
                "In German-occupied Poland during World War II, industrialist Oskar Schindler saves the lives of more than a thousand Jewish refugees by employing them in his factories.",
                List.of(Genre.DRAMA, Genre.HISTORY)
        ));






        return movies;
    }
}
