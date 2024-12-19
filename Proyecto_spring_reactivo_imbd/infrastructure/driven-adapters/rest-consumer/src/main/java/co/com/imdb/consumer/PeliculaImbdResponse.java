package co.com.imdb.consumer;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import com.fasterxml.jackson.annotation.JsonSetter;
    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import lombok.Builder;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.List;

@Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeliculaImbdResponse {

        @JsonProperty("Title")
        private String Title;

        @JsonProperty("Year")
        private String Year;

        @JsonProperty("Rated")
        private String Rated;

        @JsonProperty("Released")
        private String Released;

        @JsonProperty("Runtime")
        private String Runtime;

        @JsonProperty("Genre")
        private String Genre;

        @JsonProperty("Director")
        private String Director;

        @JsonProperty("Writer")
        private String Writer;

        @JsonProperty("Actors")
        private String Actors;

        @JsonProperty("Plot")
        private String Plot;

        @JsonProperty("Language")
        private String Language;

        @JsonProperty("Country")
        private String Country;

        @JsonProperty("Awards")
        private String Awards;

        @JsonProperty("Poster")
        private String Poster;

        @JsonProperty("Ratings")
        private List<Rating> Ratings;

        @JsonProperty("Metascore")
        private String Metascore;

        @JsonProperty("imdbRating")
        private String imdbRating;

        @JsonProperty("imdbVotes")
        private String imdbVotes;

        @JsonProperty("imdbID")
        private String imdbID;

        @JsonProperty("Type")
        private String Type;

        @JsonProperty("DVD")
        private String Dvd;

        @JsonProperty("BoxOffice")
        private String BoxOffice;

        @JsonProperty("Production")
        private String Production;

        @JsonProperty("Website")
        private String Website;

        @JsonProperty("Response")
        private String Response;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
        public static class Rating {
            @JsonProperty("Source")
            private String Source;

            @JsonProperty("Value")
            private String Value;

        }
    }

