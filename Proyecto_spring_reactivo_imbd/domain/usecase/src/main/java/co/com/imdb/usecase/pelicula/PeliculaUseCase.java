package co.com.imdb.usecase.pelicula;

import co.com.imdb.model.horario.HorarioDetalle;
import co.com.imdb.model.horario.HorarioRepository;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.model.pelicula.PeliculaImdbRepository;
import co.com.imdb.model.pelicula.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PeliculaUseCase {

    private final PeliculaRepository peliculaRepository;

    private final
    PeliculaImdbRepository peliculaImdbRepository;

    private final
    HorarioRepository horarioRepository;


    public
    Mono<List<Pelicula>> getAllPelicula (){
        return peliculaRepository.getAllPeliculas();
    }

    public
    Mono<PeliculaImdb> getDetallePeligucla(String id) throws IOException {
        return peliculaImdbRepository.getDetallePelicula(id);
    }

    public Mono<List<PeliculaImdb>> getAllMoviesSorter(List<String> sort) {

        var listaPeliculas = getAllPeliculasWithDetalle();
        var comp = Comparator.comparing(PeliculaImdb::getImdbID);

        for (String filtro:sort){
            if (filtro.contains("name")){
                if(filtro.contains(("-"))){
                    comp = Comparator.comparing(PeliculaImdb::getTitle).reversed();
                }else{
                    comp = Comparator.comparing(PeliculaImdb::getTitle);
                }

            }
            if (filtro.contains("rating")){
                if(filtro.contains(("-"))){
                    comp = Comparator.comparing(PeliculaImdb::getImdbRating).reversed();
                }else{
                    comp = Comparator.comparing(PeliculaImdb::getImdbRating);
                }

            }
            if (filtro.contains("releaseDate")){
                if(filtro.contains(("-"))){
                    comp = Comparator.comparing(PeliculaImdb::getReleased).reversed();
                }else{
                    comp = Comparator.comparing(PeliculaImdb::getReleased);
                }

            }

            Comparator<PeliculaImdb> finalComp = comp;
            listaPeliculas.map(list -> list.stream().sorted(finalComp).collect(Collectors.toList()));
        }

        return listaPeliculas;
    }

    public Mono<List<PeliculaImdb>> getAllPeliculasWithDetalle() {
        return peliculaRepository.getAllPeliculas().flatMapMany(Flux::fromIterable)
                .flatMap(pelicula -> {
                    try {
                        return getDetallePeligucla(pelicula.getIdomdb());
                    } catch (IOException e) {
                        // Manejar la excepción según tus necesidades
                        return Mono.error(e);
                    }
                })
                .collectList();
    }



}
