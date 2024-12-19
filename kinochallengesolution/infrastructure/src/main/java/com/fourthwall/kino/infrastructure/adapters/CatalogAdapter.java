package com.fourthwall.kino.infrastructure.adapters;

import com.fourthwall.kino.domain.MovieDB;
import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.MovieRateDto;
import com.fourthwall.kino.domain.ShowDto;
import com.fourthwall.kino.infrastructure.neo4j.mappers.CatalogMapper;
import com.fourthwall.kino.infrastructure.neo4j.repository.MovieRepository;
import com.fourthwall.kino.infrastructure.neo4j.repository.ShowRepository;
import com.fourthwall.kino.ports.spi.CinemaPersistencePort;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogAdapter implements CinemaPersistencePort {
    private final MovieDB movieDB;
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;

    private static final CatalogMapper MAPPER = CatalogMapper.INSTANCE;

    @Override
    public Optional<ShowDto> getShowById(final String id) {
        return showRepository.findById(id).map(MAPPER::toShowDto);
    }

    private MovieDto updateShow(final MovieDto movie, final ShowDto oldShow, final ShowDto newShow) {
        var updated = Try.of(() -> {
                    var show = ShowDto.copy(newShow, oldShow);
                    var m = CatalogMapper.INSTANCE.toMovie(movie);
                    m.addShow(CatalogMapper.INSTANCE.toShow(show));
                    return movieRepository.save(m);})
                .onSuccess(updatedShow -> log.info("show updated: {}", updatedShow.getId()))
                .onFailure(ex -> log.error("error updating show.", ex))
                .getOrElse(() -> CatalogMapper.INSTANCE.toMovie(movie));
        return MAPPER.toMovieDto(updated);
    }

    private MovieDto updateShow(final ShowDto show) {
        var movie = Option.ofOptional(movieRepository.findByMovieId(show.getMovieId()));
        var showFoundOpt = Option.ofOptional(getShowById(show.getId()));
        Either<MovieDto, MovieDto> either = showFoundOpt.isEmpty()
                ? Either.left(createShow(show))
                : Either.right(updateShow(MAPPER.toMovieDto(movie.get()), showFoundOpt.get(), show));

        return either.isLeft() ? either.getLeft() : either.get();
    }

    @Override
    public MovieDto createOrUpdateShow(final ShowDto show) {
        return Option.of(show.getId())
                .map(id-> updateShow(show))
                .getOrElse(() -> createShow(show));
    }

    @Override
    public MovieDto createShow(final ShowDto show) {
        var movieOpt = Option.ofOptional(movieRepository.findByMovieId(show.getMovieId()));
        var movie = movieOpt.map(m -> {
            m.addShow(MAPPER.toShow(show));
            return movieRepository.save(m);
        }).get();
        log.info("show created: {}", show.getId());
        return CatalogMapper.INSTANCE.toMovieDto(movie);
    }

    @Override
    public MovieDto addMovie(final MovieDto movie) {
        var toCreate = CatalogMapper.INSTANCE.toMovie(movie);
        var created = movieRepository.save(toCreate);
        log.info("movie created: {}", movie.getId());
        return MAPPER.toMovieDto(created);
    }

    @Override
    public List<MovieDto> getMovies() {
        var movies = movieRepository.findAll();
        return CatalogMapper.INSTANCE.toMovieDtos(movies);
    }

    @Override
    public List<ShowDto> getShows(final String movieId) {
        var shows = showRepository.findByMovieId(movieId);
        return MAPPER.toShowDtos(shows);
    }

    @Override
    public void rateMovie(final MovieRateDto movieRate) {
        var movieId = movieRate.getMovieId();
        Option.ofOptional(movieRepository.findByMovieId(movieId))
                .forEach(m -> {
                    m.rate(MAPPER.toMovieRate(movieRate));
                    movieRepository.save(m);
                });
    }

    @Override
    public Optional<MovieDto> getMovie(final String movieId) {
        var foundOpt = Option.ofOptional(movieRepository.findByMovieId(movieId));
        if (foundOpt.isEmpty()) {
            return Optional.empty();
        }

        var externalMovieOpt = Option.ofOptional(movieDB.getMovieInfo(movieId));
        var movie = Try.of(() -> {
            var dest = MAPPER.toMovieDto(foundOpt.get());
            var source = externalMovieOpt.get();
            return MovieDto.copy(source, dest);
        }).get();
        return Optional.of(movie);
    }

    @Override
    public List<MovieDto> getTopN(int limit) {
        var result = MAPPER.fromMovieQueryResult(movieRepository.getTopN(limit));
        return MAPPER.toMovieDtos(result);
    }
}
