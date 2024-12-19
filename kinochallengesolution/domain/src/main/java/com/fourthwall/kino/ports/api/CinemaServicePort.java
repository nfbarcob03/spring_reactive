package com.fourthwall.kino.ports.api;

import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.MovieRateDto;
import com.fourthwall.kino.domain.ShowDto;

import java.util.List;
import java.util.Optional;

public interface CinemaServicePort {
    MovieDto addMovie(MovieDto movie);
    MovieDto createOrUpdateShow(ShowDto show);
    List<ShowDto> getShows(String movieId);
    List<MovieDto> getMovies();
    Optional<ShowDto> getShowById(String showId);
    Optional<MovieDto> getMovie(String movieId);
    void rateMovie(MovieRateDto movieRate);
    List<MovieDto> getTopN(int limit);
}
