package com.fourthwall.kino.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
public class ShowDto {
    private final String id;
    private final String movieId;
    private final LocalDateTime time;
    private final BigDecimal price;

    public ShowDto(String id, String movieId, LocalDateTime time, BigDecimal price) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.movieId = movieId;
        this.time = time;
        this.price = price;
    }

    public static ShowDto copy(final ShowDto source, final ShowDto target) {
        return new ShowDto(
                source.id != null ? source.id : target.id,
                source.movieId != null ? source.movieId : target.movieId,
                source.time != null ? source.time : target.time,
                source.price != null ? source.price : target.price
        );
    }
}
