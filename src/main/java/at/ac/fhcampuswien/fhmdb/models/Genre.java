package at.ac.fhcampuswien.fhmdb.models;


import java.util.ArrayList;
import java.util.Arrays;

public enum Genre {
    ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY,
    CRIME, DRAMA, FAMILY, FANTASY, HISTORY,
    HORROR, MUSIC, MUSICAL, MYSTERY,
    ROMANCE, SCI_FI, SPORT, THRILLER, WAR, WESTERN;

    public static ArrayList<Genre> getGenres() {
        return new ArrayList<>(Arrays.asList(values()));
    }
}
