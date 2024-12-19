package com.fourthwall.kino.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ShowRequest {
    private final String id;
    private final String movieId;
    private final LocalDateTime time;
    private final BigDecimal price;

    @JsonCreator
    public ShowRequest(@JsonProperty("id") final String id, @JsonProperty("movieId") final String movieId,
                       @JsonProperty("time") final LocalDateTime time, @JsonProperty("price") final BigDecimal price) {
        this.id = id;
        this.movieId = movieId;
        this.time = time;
        this.price = price;
    }
}
