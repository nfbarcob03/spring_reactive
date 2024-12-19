package co.com.imdb.api.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonSerialize(using = ErrorDetailsSerializer.class)
@AllArgsConstructor
@Getter
public enum ErrorDetails {
    API_MOVIE_NOT_FOUND(123, "movie not found", "http://cedesistemas.edu.co/123"),
    API_HORARIO_MOVIE_NOT_FOUND(124, "horario pelicula not found", "http://cedesistemas.edu.co/124"),
    API_RETORN_BAD_REQUEST(125, "Bad request", "http://cedesistemas.edu.co/124");

    private final Integer errorCode;
    private final String errorMessage;
    private final String referenceUrl;
}
