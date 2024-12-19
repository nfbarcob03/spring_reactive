package co.com.imdb.model.error.exception;

import lombok.Getter;

public
class HorarioPeliculaNotFoundException extends RuntimeException{
    @Getter
    private final String movie;
    private   static   String MESSAGE = "Horario for movie not found: %s";

    public HorarioPeliculaNotFoundException(String movie, String e) {
        super(String.format(MESSAGE, movie));
        MESSAGE = MESSAGE + e;
        this.movie = movie;
    }
}
