package at.ac.fhcampuswien.fhmdb.models;

import net.datafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    private int releaseYear;
    private double rating;

    public Movie(String title, String description, List<Genre> genres, int releaseYear, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<Genre> getGenres() { return genres; }
    public int getReleaseYear() { return releaseYear; }
    public double getRating() { return rating; }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        Random rdm = new Random();
        Faker faker = new Faker();

        for(int i=0; i<35; i++) {
            String title = faker.movie().name();
            String description = faker.lorem().sentence();
            List<Genre> genres = getRandomGenres(1 + rdm.nextInt(3));
            int releaseYear = 1980 + rdm.nextInt(46);
            double rating = 4.0 + (rdm.nextDouble() * 6.0);

            movies.add(new Movie(title, description, genres, releaseYear, rating));
        }

        return movies;
    }

    public static List<Genre> getRandomGenres(int size) {
        Random rdm = new Random();
        List<Genre> allGenres = new ArrayList<>(Genre.getGenres());
        List<Genre> randomGenres = new ArrayList<>();

        for(int i=0; i<size; i++) {
            int randomIndex = rdm.nextInt(allGenres.size());
            randomGenres.add(allGenres.get(randomIndex));
            allGenres.remove(randomIndex);
        }
        return randomGenres;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%d) - %.1f⭐\n%s\nGenres: %s",
                title, releaseYear, rating, description, genres);
    }
}
