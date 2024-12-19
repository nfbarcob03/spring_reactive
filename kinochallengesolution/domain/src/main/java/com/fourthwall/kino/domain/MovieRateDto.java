package com.fourthwall.kino.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class MovieRateDto {
    private final String id = UUID.randomUUID().toString();
    private final String movieId;
    private final int rate;
    private final String externalRate;
    private final LocalDateTime date;
}
