package com.fourthwall.kino.connector;

import com.fourtwall.kino.client.HttpClient;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public final class OMDbConnector {
    private final String apiKey;
    private static final String OMDB_URL = "http://www.omdbapi.com/?apikey=%s&i=%s";
    private static final HttpClient HTTP_CLIENT = new HttpClient();

    public Optional<OMDbMovieDto> getMovieInfo(final String id) {
        var movie = Try.of(() ->
                        HTTP_CLIENT.call(String.format(OMDB_URL, apiKey, id),
                                Collections.emptyMap(),
                                OMDbMovieDto.class
                        )
                ).onFailure(ex -> log.error("error getting movie information", ex))
                .get();
        return Optional.ofNullable(movie);
    }
}
