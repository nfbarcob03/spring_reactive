package co.com.imdb.api;
import co.com.imdb.model.horario.HorarioDetalle;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.usecase.pelicula.PeliculaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/pelicula", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PeliculaService {
    private final PeliculaUseCase peliculaUseCase;


    @GetMapping(path = "/allPeliculas")
    public
    Mono<List<Pelicula>> getAllPeliculas() {
      return peliculaUseCase.getAllPelicula();
    }

    @GetMapping(path = "/getDetallePelicula/{idImdbPelicula}")
    public
    Mono<PeliculaImdb> getAllPeliculas(@PathVariable("idImdbPelicula") String idImdbPelicula) throws IOException {
        return peliculaUseCase.getDetallePeligucla(idImdbPelicula);
    }

}
