package com.fourthwall.kino.infrastructure.neo4j.repository;

import com.fourthwall.kino.infrastructure.neo4j.entity.Show;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface ShowRepository extends Neo4jRepository<Show, String> {
    List<Show> findByMovieId(String movieId);
}
