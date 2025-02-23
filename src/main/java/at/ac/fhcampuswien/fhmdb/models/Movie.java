package at.ac.fhcampuswien.fhmdb.models;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return String.format("%s (%d) - %.1f⭐\n%s\nGenres: %s",
                title, releaseYear, rating, description, genres);
    }

    public static List<Movie> loadMoviesFromXml() {
        List<Movie> movies = new ArrayList<>();
        try {
            // Pfad zur movies.xml anpassen (direkt im `models`-Ordner)
            File xmlFile = new File("src/main/java/at/ac/fhcampuswien/fhmdb/models/movies.xml");
            if (!xmlFile.exists()) {
                throw new RuntimeException("movies.xml not found in models folder.");
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
}
