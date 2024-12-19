package co.com.imdb.usecase.pelicula;

import co.com.imdb.model.horario.HorarioDetalle;
import co.com.imdb.model.horario.HorarioRepository;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.model.pelicula.PeliculaImdbRepository;
import co.com.imdb.model.pelicula.PeliculaRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PeliculaUseCase {

    private final PeliculaRepository peliculaRepository;

    private final
    PeliculaImdbRepository peliculaImdbRepository;

    private final
    HorarioRepository horarioRepository;
    public
    List<Pelicula> getAllPelicula (){
        return peliculaRepository.getAllPeliculas();
    }

    public
    PeliculaImdb getDetallePeligucla(String id) throws IOException {
        return peliculaImdbRepository.getDetallePelicula(id);
    }

}
