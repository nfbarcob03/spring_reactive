package com.fourthwall.kino.ports.spi;

import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.MovieRateDto;
import com.fourthwall.kino.domain.ShowDto;

import java.util.List;
import java.util.Optional;

public interface CinemaPersistencePort {
    Optional<ShowDto> getShowById(String id);
    MovieDto createOrUpdateShow(ShowDto show);
    MovieDto createShow(ShowDto show);
    MovieDto addMovie(MovieDto movie);
    List<MovieDto> getMovies();
    List<ShowDto> getShows(String movieId);
    void rateMovie(MovieRateDto movieRate);
    Optional<MovieDto> getMovie(String movieId);
    List<MovieDto> getTopN(int limit);
}
