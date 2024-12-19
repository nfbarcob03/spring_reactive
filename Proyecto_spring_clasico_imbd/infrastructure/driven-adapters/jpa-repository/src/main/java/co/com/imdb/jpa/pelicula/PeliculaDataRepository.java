package co.com.imdb.jpa.pelicula;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public
interface PeliculaDataRepository
        extends CrudRepository<PeliculaData, Integer>, QueryByExampleExecutor<PeliculaData> {

}
