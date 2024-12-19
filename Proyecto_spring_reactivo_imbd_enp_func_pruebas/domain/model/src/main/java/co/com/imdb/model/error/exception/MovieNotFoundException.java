package co.com.imdb.model.error.exception;

import lombok.Getter;

public
class MovieNotFoundException extends RuntimeException{
    @Getter
    private final String movie;
    private   static   String MESSAGE = "Movie not found: %s";

    public MovieNotFoundException(String movie, Exception e) {
        super(String.format(MESSAGE, movie));
        MESSAGE = MESSAGE + e.getMessage();
        this.movie = movie;
    }
}
