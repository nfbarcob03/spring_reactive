package co.com.imdb.model.pelicula;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public
class PeliculaImdb {
    private String       Title;
    private String       Year;
    private String       Rated;
    private String       Released;
    private String       Runtime;
    private String       Genre;
    private String       Director;
    private String       Writer;
    private String       Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String       Poster;
    private List<Rating> Ratings;
    private String       Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String Dvd;
    private String BoxOffice;
    private String Production;
    private String Website;
    private String Response;

    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rating {
        private String Source;
        private String Value;


    }
}

