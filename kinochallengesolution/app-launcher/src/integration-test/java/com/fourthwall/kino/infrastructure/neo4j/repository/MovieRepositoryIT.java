package com.fourthwall.kino.infrastructure.neo4j.repository;

import com.fourthwall.kino.infrastructure.neo4j.entity.Movie;
import com.fourthwall.kino.infrastructure.neo4j.entity.MovieRate;
import com.fourthwall.kino.infrastructure.neo4j.mappers.CatalogMapper;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@Testcontainers
public class MovieRepositoryIT {
    private static final String NEO4J_USER = "neo4j";
    private static final String NEO4J_PWD = "s3cr3t";

    @Container
    static Neo4jContainer<?> neo4jContainer =
            new Neo4jContainer<>("neo4j:4.4.4")
                    .withStartupTimeout(Duration.ofMinutes(5))
                    .withAdminPassword(NEO4J_PWD);

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> NEO4J_USER);
        registry.add("spring.neo4j.authentication.password", neo4jContainer::getAdminPassword);
    }

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void should_get_movie_created_at_startup() {
        var movie = movieRepository.findByMovieId("tt0232500");
        assertThat(movie.isPresent(), is(Boolean.TRUE));
    }

    @Test
    void should_get_top_n_movies() {
        insertMovies();

        var results = movieRepository.getTopN(3);
        var movies = CatalogMapper.INSTANCE.fromMovieQueryResult(results);

        assertThat(movies.size(), is(results.size()));
    }

    private void insertMovies() {
        Stream.generate(MovieRepositoryIT::dummyMovie)
                .limit(10)
                .forEach(movieRepository::save);
    }

    private static Movie dummyMovie() {
        var faker = new Faker();
        var movieId =RandomStringUtils.randomAlphanumeric(10);
        return new Movie(
                UUID.randomUUID().toString(),
                movieId,
                faker.pokemon().name(),
                faker.lorem().sentence(),
                LocalDate.now(),
                RandomStringUtils.randomNumeric(1),
                null,
                Stream.generate(() -> dummyMovieRate(movieId)).limit(4).collect(Collectors.toSet()),
                Collections.emptySet()
        );
    }

    private static MovieRate dummyMovieRate(final String movieId) {
        return new MovieRate(
                UUID.randomUUID().toString(),
                movieId,
                RandomUtils.nextInt(1, 6),
                LocalDateTime.now()
        );
    }
}
