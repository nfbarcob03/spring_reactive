package co.com.imdb.model.error.exception;

import lombok.Getter;

public
class BadRequestException extends RuntimeException {
    @Getter
    private final String movie;
    private   static   String MESSAGE = "invalid id OMBD movie: %s";

    public BadRequestException(String movie, String message) {
        super(String.format(MESSAGE, movie));
        MESSAGE = MESSAGE + message;
        this.movie = movie;
    }
}
