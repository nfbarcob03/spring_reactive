package co.com.imdb.jpa.horario;

import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public
interface HorarioDataRepository
        extends ReactiveCrudRepository<HorarioData, Integer> {

    Flux<HorarioData> findByIdPelicula(Integer idPelicula);
}
