package co.com.imdb.jpa.horario;

import co.com.imdb.jpa.pelicula.PeliculaData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public
interface HorarioDataRepository
        extends CrudRepository<HorarioData, Integer>, QueryByExampleExecutor<HorarioData> {

    List<HorarioData> findByIdPelicula(Integer idPelicula);
}
