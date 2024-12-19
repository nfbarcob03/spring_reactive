package com.fourthwall.kino.controller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class MovieRepresentation extends RepresentationModel<MovieRepresentation> {
    private final String id;
    private final String movieId;
    private final String name;
    private final String description;
    private final LocalDate releaseDate;
    private final String rating;
    private final String runtime;
    private final BigDecimal avgRate;
    private final Set<ShowRepresentation> shows = new HashSet<>();
}
