package co.com.imdb.api;
import co.com.imdb.model.horario.Horario;
import co.com.imdb.model.horario.HorarioDetalle;
import co.com.imdb.usecase.horario.HoraioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildfly.common.annotation.NotNull;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/horario", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class HorarioService {
    private final HoraioUseCase horarioUsecas;

    @GetMapping(path = "/getHorarioPelicula/{idPelicula}")
    public
    Mono<ResponseEntity<HorarioDetalle>>getHorarioPelicula(@PathVariable("idPelicula") @NotNull Integer idPelicula) throws IOException {
        return horarioUsecas.getHorarioPelioculaByIdPelicula(idPelicula)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK));
    }


        @GetMapping(path = "/getAllHorarioPelicula")
    public
        Mono<ResponseEntity<List<HorarioDetalle>>> getAllHorarioPelicula() throws IOException {
        return horarioUsecas.getPeliculasDisponiblesHorario()
                .map(pelicula -> new ResponseEntity<>(pelicula, HttpStatus.OK));
    }

    @PostMapping(path = "/saveHorarioPelicula")
    public
    Mono<ResponseEntity<Horario>> saveHorarioPelicula(@RequestBody Mono<Horario> horarioRequest){
        return horarioRequest.flatMap(horarioUsecas::saveHorario)
                .map(horario -> new ResponseEntity<>(horario, HttpStatus.CREATED));
    }

    @PutMapping(path = "/updateHorarioPelicula")
    public
    Mono<ResponseEntity<Horario>> updateHorarioPelicula(@RequestBody Mono<Horario> horarioRequest){
        return horarioRequest.flatMap(horarioUsecas::updateHorario)
                .map(horario -> new ResponseEntity<>(horario, HttpStatus.NO_CONTENT));
    }
}
