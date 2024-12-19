package co.com.imdb.api;
import co.com.imdb.model.horario.Horario;
import co.com.imdb.model.horario.HorarioDetalle;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.usecase.horario.HoraioUseCase;
import co.com.imdb.usecase.pelicula.PeliculaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/horario", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class HorarioService {
    private final HoraioUseCase horarioUsecas;

    @GetMapping(path = "/getHorarioPelicula/{idPelicula}")
    public
    HorarioDetalle getHorarioPelicula(@PathVariable("idPelicula") Integer idPelicula) throws IOException {
        return horarioUsecas.getHorarioPelioculaByIdPelicula(idPelicula);
    }

    @GetMapping(path = "/getAllHorarioPelicula")
    public
    List<HorarioDetalle> getAllHorarioPelicula() throws IOException {
        return horarioUsecas.getPeliculasDisponiblesHorario();
    }

    @PostMapping(path = "/saveHorarioPelicula")
    public
    void saveHorarioPelicula(@RequestBody Horario horario){
        horarioUsecas.saveHorario(horario);
    }

    @PutMapping(path = "/updateHorarioPelicula")
    public
    void updateHorarioPelicula(@RequestBody Horario horario){
        horarioUsecas.updateHorario(horario);
    }
}
