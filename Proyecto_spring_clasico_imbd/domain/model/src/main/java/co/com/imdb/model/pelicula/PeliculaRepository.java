package co.com.imdb.model.pelicula;

import co.com.imdb.model.pelicula.Pelicula;

import java.util.List;

public
interface PeliculaRepository {
    List<Pelicula> getAllPeliculas();
    Pelicula getPeliculaById(Integer id);
}
