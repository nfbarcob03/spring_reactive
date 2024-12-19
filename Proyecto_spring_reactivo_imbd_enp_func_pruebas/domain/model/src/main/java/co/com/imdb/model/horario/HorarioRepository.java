package co.com.imdb.model.horario;

import co.com.imdb.model.pelicula.Pelicula;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public
interface HorarioRepository {
    Mono<List<Horario>> getAllHorario();
    Mono<List<Horario>> getHorarioByIdPelicula(Integer idPelicula);
    Mono<Horario> updateHorarioPeliocula(Horario horario);

    Mono<Horario> saveHorarioPeliocula(Horario horario);

}
