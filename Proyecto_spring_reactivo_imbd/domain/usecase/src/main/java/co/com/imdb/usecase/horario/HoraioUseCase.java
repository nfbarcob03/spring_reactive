package co.com.imdb.usecase.horario;

import co.com.imdb.model.horario.Horario;
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
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HoraioUseCase {

    private final PeliculaRepository peliculaRepository;

    private final
    PeliculaImdbRepository peliculaImdbRepository;

    private final
    HorarioRepository horarioRepository;

    public
    Mono<HorarioDetalle> getHorarioPelioculaByIdPelicula(Integer id) {

        var horario = horarioRepository.getHorarioByIdPelicula(id);
        var pelicula = peliculaRepository.getPeliculaById(id);
        var monoId = pelicula.map(Pelicula::getIdomdb);

        var peliculaImbd =  monoId.flatMap(idImbd -> {
            try {
                return peliculaImdbRepository.getDetallePelicula(idImbd);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return Mono.zip(horario, peliculaImbd, pelicula)
                .map(tuple -> consolidateHorarioDetalle(tuple.getT1(), tuple.getT2(), tuple.getT3()));

    }

    public
    Mono<List<HorarioDetalle>> getPeliculasDisponiblesHorario(){
        var peliculas = peliculaRepository.getAllPeliculas();
        return  peliculas.flatMapMany(peliculaList -> Flux.fromIterable(peliculaList)).flatMap(pelicula ->
                                                                      getHorarioPelioculaByIdPelicula(pelicula.getId()))
                .collectList();


    }

    public Mono<Horario> saveHorario (Horario horario){
        return horarioRepository.saveHorarioPeliocula(horario);
    }

    public  Mono<Horario> updateHorario (Horario horario){
        return horarioRepository.updateHorarioPeliocula(horario);
    }

    private HorarioDetalle consolidateHorarioDetalle(List<Horario> horario, PeliculaImdb peliculaImbd, Pelicula pelicula){
        HorarioDetalle horarioDetalle = new HorarioDetalle();
        horarioDetalle.setHorarios(horario);
        horarioDetalle.setGenero(peliculaImbd.getGenre());
        horarioDetalle.setNombre(pelicula.getNombre());
        horarioDetalle.setSinopsis(peliculaImbd.getPlot());
        horarioDetalle.setTiempoDuracion(peliculaImbd.getRuntime());
        return horarioDetalle;
    }

}
