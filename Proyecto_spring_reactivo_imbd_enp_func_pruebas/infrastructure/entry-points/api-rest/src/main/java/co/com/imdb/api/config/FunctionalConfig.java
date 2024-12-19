package co.com.imdb.api.config;

import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.usecase.pelicula.PeliculaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class FunctionalConfig {

    private final PeliculaUseCase peliculaUseCase;




    @Bean
    public RouterFunction<ServerResponse> getPeliculaSort() {
        return route(GET("/pelicula/order-by"),
                     req -> ok().body(peliculaUseCase.getAllMoviesSorter(req.queryParams().get("sort")), PeliculaImdb.class));

    }

    @Bean
    public RouterFunction<ServerResponse> getAllPeliculasWithDetalle() {
        return route(GET("/pelicula/allDetail"),
                     req -> ok().body(peliculaUseCase.getAllPeliculasWithDetalle(), PeliculaImdb.class));

    }


}
