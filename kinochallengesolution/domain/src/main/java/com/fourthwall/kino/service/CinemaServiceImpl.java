package com.fourthwall.kino.service;

import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.MovieRateDto;
import com.fourthwall.kino.domain.ShowDto;
import com.fourthwall.kino.ports.api.CinemaServicePort;
import com.fourthwall.kino.ports.spi.CinemaPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaServicePort {
    private final CinemaPersistencePort cinemaPersistencePort;

    @Override
    public MovieDto addMovie(final MovieDto movie) {
        return cinemaPersistencePort.addMovie(movie);
    }

    @Override
    public MovieDto createOrUpdateShow(final ShowDto show) {
        return cinemaPersistencePort.createOrUpdateShow(show);
    }

    @Override
    public List<ShowDto> getShows(final String movieId) {
        return cinemaPersistencePort.getShows(movieId);
    }

    @Override
    public List<MovieDto> getMovies() {
        return cinemaPersistencePort.getMovies();
    }

    @Override
    public Optional<ShowDto> getShowById(String showId) {
        return cinemaPersistencePort.getShowById(showId);
    }

    @Override
    public void rateMovie(final MovieRateDto movieRate) {
        cinemaPersistencePort.rateMovie(movieRate);
    }

    @Override
    public List<MovieDto> getTopN(int limit) {
        return cinemaPersistencePort.getTopN(limit);
    }

    @Override
    public Optional<MovieDto> getMovie(final String movieId) {
        return cinemaPersistencePort.getMovie(movieId);
    }
}
