package co.com.imdb.model.pelicula;

import co.com.imdb.model.pelicula.PeliculaImdb;

import java.io.IOException;

public
interface PeliculaImdbRepository {
    PeliculaImdb getDetallePelicula(String id) throws IOException;
}
