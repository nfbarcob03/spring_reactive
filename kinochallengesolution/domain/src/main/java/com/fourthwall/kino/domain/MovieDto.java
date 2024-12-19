package com.fourthwall.kino.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Getter
public class MovieDto {
    private final String id;
    private final String movieId;
    private final String name;
    private final String description;
    private final LocalDate releaseDate;
    private final String rating;
    private final String runtime;
    private final BigDecimal avgRate;

    private final Set<ShowDto> shows;

    public MovieDto(String id, String movieId, String name, String description, LocalDate releaseDate,
                    String rating, String runtime, BigDecimal avgRate, Set<ShowDto> shows) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.movieId = movieId;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.runtime = runtime;
        this.avgRate = avgRate;
        this.shows = shows;
    }

    public static MovieDto copy(MovieDto source, MovieDto target) {
        return new MovieDto(
                source.id != null ? source.id : target.id,
                source.movieId != null ? source.movieId : target.movieId,
                source.name != null ? source.name : target.name,
                source.description != null ? source.description : target.description,
                source.releaseDate != null ? source.releaseDate : target.releaseDate,
                source.rating != null ? source.rating : target.rating,
                source.runtime != null ? source.runtime : target.runtime,
                source.avgRate != null ? source.avgRate : target.avgRate,
                source.shows != null ?  source.shows : target.shows
        );
    }
}
