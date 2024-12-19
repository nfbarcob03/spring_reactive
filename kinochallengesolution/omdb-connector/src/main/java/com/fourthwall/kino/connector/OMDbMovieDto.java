package com.fourthwall.kino.connector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OMDbMovieDto {
    private final Response response;
    private final String movieId;
    private final String title;
    private final String description;
    private final LocalDate released;
    private final List<RatingDto> ratings;
    private final String runtime;

    @JsonCreator
    public OMDbMovieDto(@JsonProperty("Response") Response response, @JsonProperty("imdbID") String movieId,
                        @JsonProperty("Title") String title, @JsonProperty("Plot") String description,
                        @JsonProperty("Released")
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd MMM yyyy")LocalDate released,
                        @JsonProperty("Ratings") List<RatingDto> ratings, @JsonProperty("Runtime") String runtime) {
        this.response = response;
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.released = released;
        this.ratings = ratings;
        this.runtime = runtime;
    }

    @AllArgsConstructor
    @Getter
    public enum Response {
        TRUE("True"),
        FALSE("False");

        @JsonValue
        String value;
    }
}
