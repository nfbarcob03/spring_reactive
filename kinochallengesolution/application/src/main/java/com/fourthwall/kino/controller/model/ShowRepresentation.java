package com.fourthwall.kino.controller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShowRepresentation extends RepresentationModel<ShowRepresentation> {
    private final String id;
    private final String movieId;
    private final LocalDateTime time;
    private final BigDecimal price;
}
