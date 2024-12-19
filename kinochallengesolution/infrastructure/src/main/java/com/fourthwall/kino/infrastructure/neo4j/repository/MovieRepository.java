package com.fourthwall.kino.infrastructure.neo4j.repository;

import com.fourthwall.kino.infrastructure.neo4j.entity.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, String>  {
    Optional<Movie> findByMovieId(String movieId);

    @Query("MATCH (movie:Movie)-[rt:RATE]->(mr:MovieRate) RETURN movie, toString(avg(mr.rate)) as avgRate ORDER BY avgRate DESC LIMIT $limit")
    List<MovieQueryResult> getTopN(@Param("limit") int limit);

    @Data
    @NoArgsConstructor
    class MovieQueryResult {
        Movie movie;
        private BigDecimal avgRate;
    }
}
