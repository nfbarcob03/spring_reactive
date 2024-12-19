package co.com.imdb.jpa.pelicula;

import co.com.imdb.jpa.helper.AdapterOperations;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
class PeliculaRepositoryAdapter extends AdapterOperations<Pelicula, PeliculaData, Integer, PeliculaDataRepository>
implements PeliculaRepository {
    public
    PeliculaRepositoryAdapter(PeliculaDataRepository repository, ObjectMapper objectMapper) {
        super(repository, objectMapper, d -> objectMapper.mapBuilder(d,
                                                                                      Pelicula.PeliculaBuilder.class)
                .build());
    }

    @Override
    public
    List<Pelicula> getAllPeliculas() {
        return super.toList(repository.findAll());
    }

    @Override
    public
    Pelicula getPeliculaById(Integer id) {
        return super.toEntity(repository.findById(id).orElse(null));
    }


}

