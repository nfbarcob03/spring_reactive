package co.com.imdb.model.pelicula;

import co.com.imdb.model.pelicula.Pelicula;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public
interface PeliculaRepository {
    Mono<List<Pelicula>> getAllPeliculas();
    Mono<Pelicula> getPeliculaById(Integer id);
}
