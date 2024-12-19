package com.fourthwall.kino.domain;

import java.util.Optional;

public interface MovieDB {
    String name();
    Optional<MovieDto> getMovieInfo(String movieId);
}
