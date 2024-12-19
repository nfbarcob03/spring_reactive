package com.fourthwall.kino.infrastructure.neo4j.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@Node
public class Movie {
    @Id @GeneratedValue(UUIDStringGenerator.class)
    private final String id;
    private final String movieId;
    private final String name;
    private final String description;
    private final LocalDate releaseDate;
    private final String runtime;
    private final BigDecimal avgRate;

    @Relationship(type = "RATE")
    private final Set<MovieRate> rates;

    @Relationship(type = "SHOWTIME")
    private final Set<Show> shows;

    public void rate(final MovieRate rate) {
        rates.add(rate);
    }

    public void addShow(final Show show) {
        shows.remove(show);
        shows.add(show);
    }
}
