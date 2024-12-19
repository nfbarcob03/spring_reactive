package co.com.imdb.model.pelicula;

import co.com.imdb.model.pelicula.PeliculaImdb;
import reactor.core.publisher.Mono;

import java.io.IOException;

public
interface PeliculaImdbRepository {
    Mono<PeliculaImdb> getDetallePelicula(String id) throws IOException;
}
