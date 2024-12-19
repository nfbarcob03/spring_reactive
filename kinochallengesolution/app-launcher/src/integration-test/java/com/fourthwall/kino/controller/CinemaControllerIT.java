package com.fourthwall.kino.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourthwall.kino.controller.mapper.CatalogRepresentationMapper;
import com.fourthwall.kino.controller.model.MovieRequest;
import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.ports.api.CinemaServicePort;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@WebMvcTest(CinemaController.class)
@Slf4j
public class CinemaControllerIT {
    private static final CatalogRepresentationMapper MAPPER = CatalogRepresentationMapper.INSTANCE;

    @MockBean
    private CinemaServicePort cinemaServicePort;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_get_movie_by_id() throws Exception {
        var dummyMovie = dummyMovie();


        given(cinemaServicePort.getMovie(ArgumentMatchers.anyString()))
                .willReturn(Optional.of(MAPPER.fromMovieRequest(dummyMovie)));

        mockMvc
                .perform(get(format("/movies/%s", dummyMovie.getMovieId()))
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dummyMovie.getId()))
                .andExpect(jsonPath("$.movieId").value(dummyMovie.getMovieId()))
                .andExpect(
                        jsonPath("$._links.self.href")
                                .value(format("http://localhost/movies/%s", dummyMovie.getMovieId()))
                ).andReturn();
    }

    @Test
    void should_get_all_movies() throws Exception {
        var dummyMovies = dummyMovies(5);
        given(cinemaServicePort.getMovies()).willReturn(MAPPER.fromMovieRequest(dummyMovies));

        mockMvc
                .perform(get("/movies")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(dummyMovies.size()))
                .andExpect(jsonPath("$[0].id").value(dummyMovies.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(dummyMovies.get(1).getId()))
                .andReturn();
    }

    @Test
    void should_create_movie() throws Exception {
        var dummyMovie = dummyMovie();

        given(cinemaServicePort.addMovie(any())).willReturn(MAPPER.fromMovieRequest(dummyMovie));

        mockMvc
                .perform(post("/movies")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dummyMovie))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(dummyMovie.getId()))
                .andExpect(jsonPath("$.movieId").value(dummyMovie.getMovieId()))
                .andExpect(
                        jsonPath("$._links.self.href")
                                .value(format("http://localhost/movies/%s", dummyMovie.getMovieId())))
                .andReturn();
    }

    private MovieRequest dummyMovie() {
        var faker = new Faker();
        return new MovieRequest(
                UUID.randomUUID().toString(),
                RandomStringUtils.randomAlphanumeric(10),
                faker.pokemon().name()
        );
    }

    public List<MovieRequest> dummyMovies(final int size) {
        return Stream.generate(this::dummyMovie).limit(size).collect(Collectors.toList());
    }
}