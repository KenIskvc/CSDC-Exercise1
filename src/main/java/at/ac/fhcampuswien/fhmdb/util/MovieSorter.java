package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;
import java.util.List;

public class MovieSorter {

    public enum SortBy {
        TITLE,
        RATING,
        RELEASE_YEAR
    }

    public static void sortMovies(List<Movie> movies, SortBy sortBy, boolean ascending) {
        Comparator<Movie> comparator;

        switch (sortBy) {
            case RATING:
                comparator = Comparator.comparing(Movie::getRating);
                break;
            case RELEASE_YEAR:
                comparator = Comparator.comparing(Movie::getReleaseYear);
                break;
            case TITLE:
            default:
                comparator = Comparator.comparing(Movie::getTitle);
                break;
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        movies.sort(comparator);
    }
}
