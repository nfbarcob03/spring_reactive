package co.com.imdb.jpa.pelicula;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;



public
interface PeliculaDataRepository
        extends ReactiveCrudRepository<PeliculaData, Integer>{

}
