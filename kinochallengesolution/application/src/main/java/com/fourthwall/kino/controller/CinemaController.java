package com.fourthwall.kino.controller;

import com.fourthwall.kino.controller.mapper.CatalogRepresentationMapper;
import com.fourthwall.kino.controller.model.MovieRepresentation;
import com.fourthwall.kino.controller.model.MovieRequest;
import com.fourthwall.kino.controller.model.ShowRepresentation;
import com.fourthwall.kino.controller.model.ShowRequest;
import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.MovieRateDto;
import com.fourthwall.kino.domain.ShowDto;
import com.fourthwall.kino.ports.api.CinemaServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "kino", description = "Kino Cinema API")
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/movies")
public class CinemaController {
    private static final CatalogRepresentationMapper MAPPER = CatalogRepresentationMapper.INSTANCE;

    private final CinemaServicePort cinemaServicePort;

    @Operation(summary = "create new movie")
    @ApiResponse(
            responseCode = "201",
            description = "movie created",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MovieRepresentation.class))
            })
    @PostMapping
    public HttpEntity<MovieRepresentation> addMovie(
            @RequestBody(
                    required = true,
                    description = "movie information",
                    content = @Content(schema = @Schema(implementation = MovieRequest.class)))
            @org.springframework.web.bind.annotation.RequestBody
            final MovieRequest movie) {
        var created = Option.of(cinemaServicePort.addMovie(MAPPER.fromMovieRequest(movie)));
        var entity = created
                .map(m -> addLinks(CatalogRepresentationMapper.INSTANCE.toMovieRepresentation(m), created));
        return entity
                .map(m -> new ResponseEntity<>(m, HttpStatus.CREATED))
                .get();
    }

    @Operation(summary = "creates or updates a show")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "movie created or updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MovieRepresentation.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "input not valid",
                    content = @Content
            )
    })
    @PutMapping("/shows")
    public HttpEntity<MovieRepresentation> createOrUpdateShow(
            @RequestBody(
                    required = true,
                    description = "show information",
                    content = @Content(schema = @Schema(implementation = ShowRequest.class)))
            @org.springframework.web.bind.annotation.RequestBody
            final ShowRequest show) {
        return Try.of(() -> {
            var updated = Option.of(cinemaServicePort.createOrUpdateShow(MAPPER.fromShowRequest(show)));
            var entity = updated
                    .map(m -> addLinks(MAPPER.toMovieRepresentation(m), updated));
            return new ResponseEntity<>(entity.get(), HttpStatus.OK);
        }).onFailure(ex -> log.error("error uptating ", ex))
                .getOrElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "retrieves all show times for a movie")
    @ApiResponse(
            responseCode = "200",
            description = "success",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShowRepresentation[].class))
            })
    @GetMapping("/{movieId}/shows")
    public HttpEntity<java.util.List<ShowRepresentation>> getShows(
            @Parameter(required = true, description = "the movie id")
            @PathVariable
            final String movieId) {
        var list = List.ofAll(cinemaServicePort.getShows(movieId));
        var entity = list
                .map(s -> addLinks(MAPPER.toShowRepresentation(s), Option.of(s)))
                .collect(List.collector())
                .toJavaList();
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Operation(summary = "get show time by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Show Time",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ShowRepresentation.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "showtime not found",
                    content = @Content
            )
    })
    @GetMapping("/shows/{showId}")
    public HttpEntity<ShowRepresentation> getShowById(
            @Parameter(required = true, description = "the show id")
            @PathVariable
            final String showId) {
        var show = Option.ofOptional(cinemaServicePort.getShowById(showId));
        var entity = show
                .map(s -> addLinks(CatalogRepresentationMapper.INSTANCE.toShowRepresentation(s), show));
        return entity
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .getOrElse(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "rates a movie")
    @ApiResponse(
            responseCode = "201",
            description = "movie rated",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MovieRateDto.class))
            })
    @PostMapping("/{movieId}/rates")
    public HttpEntity<MovieRateDto> rateMovie(
            @Parameter(required = true, description = "the movie id")
            @PathVariable
            final String movieId,
            @Parameter(required = true, description = "the rate value")
            @RequestParam
            final int rate) {
        final var dto = MovieRateDto.builder()
                .movieId(movieId)
                .rate(rate)
                .date(LocalDateTime.now())
                .build();
        cinemaServicePort.rateMovie(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(summary = "get movie by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "movie",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MovieRepresentation.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "movie not found",
                    content = @Content
            )
    })
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieRepresentation> getMovie(
            @Parameter(required = true, description = "the movie id")
            @PathVariable
            final String movieId) {
        var movie = Option.ofOptional(cinemaServicePort.getMovie(movieId));
        var entity = movie
                .map(m -> addLinks(CatalogRepresentationMapper.INSTANCE.toMovieRepresentation(m), movie));
        return entity
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .getOrElse(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "get top rated n movies")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "movie",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MovieRepresentation[].class))
                    })
    })
    @GetMapping("/top/{limit}")
    public HttpEntity<java.util.List<MovieRepresentation>> getTopN(
            @Parameter(required = true, description = "the limit of the query")
            @PathVariable
            final int limit) {
        List<MovieDto> list = List.ofAll(cinemaServicePort.getTopN(limit));
        var entity = list
                .map(m -> addLinks(CatalogRepresentationMapper.INSTANCE.toMovieRepresentation(m), Option.of(m)))
                .collect(List.collector())
                .toJavaList();
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Operation(summary = "get all movies in catalog")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "all movies",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MovieRepresentation[].class))
                    })
    })
    @GetMapping
    public HttpEntity<java.util.List<MovieRepresentation>> getMovies() {
        List<MovieDto> list = List.ofAll(cinemaServicePort.getMovies());
        var entity = list
                .map(m -> addLinks(CatalogRepresentationMapper.INSTANCE.toMovieRepresentation(m), Option.of(m)))
                .collect(List.collector())
                .toJavaList();
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    private ShowRepresentation addLinks(final ShowRepresentation showRep, final Option<ShowDto> show) {
        return show.map(s -> {
            showRep.add(linkTo(methodOn(CinemaController.class).getShowById(s.getId())).withSelfRel());
            return showRep;
        }).get();
    }

    private MovieRepresentation addLinks(final MovieRepresentation movieRep, final Option<MovieDto> movie) {
        return movie.map(m -> {
            movieRep.add(linkTo(methodOn(CinemaController.class).getMovie(m.getMovieId())).withSelfRel());
            Optional.ofNullable(movieRep.getShows())
                    .ifPresent(x -> x.forEach(
                            s -> s.add(linkTo(methodOn(CinemaController.class).getShowById(s.getId())).withSelfRel())
                    ));
            return movieRep;
        }).get();
    }
}