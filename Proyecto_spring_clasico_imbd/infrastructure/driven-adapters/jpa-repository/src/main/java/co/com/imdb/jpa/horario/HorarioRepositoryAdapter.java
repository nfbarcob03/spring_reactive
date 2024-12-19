package co.com.imdb.jpa.horario;

import co.com.imdb.jpa.helper.AdapterOperations;
import co.com.imdb.jpa.pelicula.PeliculaData;
import co.com.imdb.jpa.pelicula.PeliculaDataRepository;
import co.com.imdb.model.horario.Horario;
import co.com.imdb.model.horario.HorarioRepository;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
class HorarioRepositoryAdapter extends AdapterOperations<Horario, HorarioData, Integer, HorarioDataRepository>
implements HorarioRepository {
    public
    HorarioRepositoryAdapter(HorarioDataRepository repository, ObjectMapper objectMapper) {
        super(repository, objectMapper, d -> objectMapper.mapBuilder(d,
                                                                                      Horario.HorarioBuilder.class)
                .build());
    }


    @Override
    public
    List<Horario> getAllHorario() {
        return super.toList(repository.findAll());
    }

    @Override
    public
    List<Horario> getHorarioByIdPelicula(Integer idPelicula) {
        return super.toList(repository.findByIdPelicula(idPelicula));
    }

    @Override
    public
    void updateHorarioPeliocula(Horario horario) {
        repository.save(mapper.map(horario, HorarioData.class));
    }

    @Override
    public
    void saveHorarioPeliocula(Horario horario) {
        repository.save(mapper.map(horario, HorarioData.class));
    }
}

