package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import java.util.Comparator;
import java.util.List;

public class MovieSorter {
    public static void sortMovies(List<Movie> movies, boolean ascending) {
        movies.sort(ascending
                ? Comparator.comparing(Movie::getTitle)
                : Comparator.comparing(Movie::getTitle).reversed());
    }
}
