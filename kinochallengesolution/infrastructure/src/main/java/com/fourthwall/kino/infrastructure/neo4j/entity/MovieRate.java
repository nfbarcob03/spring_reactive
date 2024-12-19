package com.fourthwall.kino.infrastructure.neo4j.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;

@Node
@EqualsAndHashCode(of = "id")
@Data
public class MovieRate {
    @Id @GeneratedValue(UUIDStringGenerator.class)
    private final String id;
    private final String movieId;
    private final int rate;
    private final LocalDateTime date;
}
