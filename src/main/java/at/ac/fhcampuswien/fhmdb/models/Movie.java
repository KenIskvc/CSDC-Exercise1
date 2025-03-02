package at.ac.fhcampuswien.fhmdb.models;

import net.datafaker.Faker;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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

    public static List<Movie> loadMoviesFromXml() {
        List<Movie> movies = new ArrayList<>();
        try {
            File xmlFile = new File("src/main/resources/at/ac/fhcampuswien/fhmdb/movies.xml");
            if (!xmlFile.exists()) {
                throw new RuntimeException("movies.xml not found. Please check the location of the XML file or if it exists.");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("movie");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    int releaseYear = Integer.parseInt(element.getElementsByTagName("releaseYear").item(0).getTextContent());
                    double rating = Double.parseDouble(element.getElementsByTagName("rating").item(0).getTextContent());

                    List<Genre> genres = new ArrayList<>();
                    NodeList genreNodes = element.getElementsByTagName("genre");
                    for (int j = 0; j < genreNodes.getLength(); j++) {
                        genres.add(Genre.valueOf(genreNodes.item(j).getTextContent()));
                    }

                    movies.add(new Movie(title, description, genres, releaseYear, rating));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) - %.1f⭐\n%s\nGenres: %s",
                title, releaseYear, rating, description, genres);
    }
}
