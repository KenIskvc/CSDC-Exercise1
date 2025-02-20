package at.ac.fhcampuswien.fhmdb.models;



import java.util.Arrays;
import java.util.List;

public enum Genre {
    ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY,
    CRIME, DRAMA, FAMILY, FANTASY, HISTORY,
    HORROR, MUSIC, MUSICAL, MYSTERY,
    ROMANCE, SCI_FI, SPORT, THRILLER, WAR, WESTERN;

    public static List<Genre> getGenres() {
        return Arrays.asList(values());

    }
}
