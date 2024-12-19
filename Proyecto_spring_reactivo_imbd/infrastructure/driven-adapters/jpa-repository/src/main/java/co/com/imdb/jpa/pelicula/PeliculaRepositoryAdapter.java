package co.com.imdb.jpa.pelicula;

import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@AllArgsConstructor
@Getter
public
class PeliculaRepositoryAdapter
implements PeliculaRepository {
    private final PeliculaDataRepository repository;

    private static final ObjectMapper mapper = new ObjectMapperImp();

    @Override
    public
    Mono<List<Pelicula>> getAllPeliculas() {
        return repository.findAll().map(PeliculaRepositoryAdapter::castPeliculaData).collectList();
    }

    @Override
    public
    Mono<Pelicula> getPeliculaById(Integer id) {
        return repository.findById(id).map(PeliculaRepositoryAdapter::castPeliculaData);
    }

    private static
    Pelicula castPeliculaData(PeliculaData peliculaData){
       return  mapper.map(peliculaData, Pelicula.class);
    }


}

