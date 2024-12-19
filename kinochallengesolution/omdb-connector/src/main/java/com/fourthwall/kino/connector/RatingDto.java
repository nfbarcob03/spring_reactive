package com.fourthwall.kino.connector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RatingDto {
    private final String source;
    private final String value;

    @JsonCreator
    public RatingDto(@JsonProperty("Source") String source, @JsonProperty("Value") String value) {
        this.source = source;
        this.value = value;
    }
}
