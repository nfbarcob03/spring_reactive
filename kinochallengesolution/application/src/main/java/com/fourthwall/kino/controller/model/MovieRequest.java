package com.fourthwall.kino.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MovieRequest {
    private final String id;
    private final String movieId;
    private final String name;

    @JsonCreator
    public MovieRequest(@JsonProperty("id") String id, @JsonProperty("movieId") String movieId,
                        @JsonProperty("name") String name) {
        this.id = id;
        this.movieId = movieId;
        this.name = name;
    }
}
