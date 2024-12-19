package com.fourthwall.kino.infrastructure.omdb;

import com.fourthwall.kino.connector.OMDbConnector;
import com.fourthwall.kino.domain.MovieDB;
import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.infrastructure.omdb.mappers.MovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Repository
public class OmdbRepository implements MovieDB {
    private static final String OMDB = "OMDb";

    private final OMDbConnector ombdConnector;

    @Override
    public String name() {
        return OMDB;
    }

    @Override
    public Optional<MovieDto> getMovieInfo(final String movieId) {
        return ombdConnector.getMovieInfo(movieId).map(MovieMapper.INSTANCE::toMovie);
    }
}
