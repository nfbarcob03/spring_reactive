package co.com.imdb.jpa.horario;

import co.com.imdb.jpa.helper.AdapterOperations;
import co.com.imdb.jpa.pelicula.PeliculaData;
import co.com.imdb.jpa.pelicula.PeliculaDataRepository;
import co.com.imdb.model.horario.Horario;
import co.com.imdb.model.horario.HorarioRepository;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
class HorarioRepositoryAdapter
implements HorarioRepository {


    private static final ObjectMapper mapper = new ObjectMapperImp();

    private final HorarioDataRepository repository;


    @Override
    public
    Mono<List<Horario>> getAllHorario() {
        return repository.findAll().map(HorarioRepositoryAdapter::castHorarioData).collectList();
    }

    @Override
    public
    Mono<List<Horario>> getHorarioByIdPelicula(Integer idPelicula) {
        return repository.findByIdPelicula(idPelicula).map(HorarioRepositoryAdapter::castHorarioData).collectList();
    }

    @Override
    public
    Mono<Horario> updateHorarioPeliocula(Horario horario) {
        return repository.save(mapper.map(horario,HorarioData.class)).map(HorarioRepositoryAdapter::castHorarioData);
    }

    @Override
    public
    Mono<Horario> saveHorarioPeliocula(Horario horario) {
        return repository.save(mapper.map(horario,HorarioData.class)).map(HorarioRepositoryAdapter::castHorarioData);
    }

    private static
    Horario castHorarioData (HorarioData horarioData){
        return  mapper.map(horarioData, Horario.class);
    }

    HorarioData castHorario (Horario horario){
        return  mapper.map(horario, HorarioData.class);
    }
}





