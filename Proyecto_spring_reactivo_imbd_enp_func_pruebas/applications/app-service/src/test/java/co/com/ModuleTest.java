package co.com;

import co.com.imdb.MainApplication;
import co.com.imdb.api.config.FunctionalConfig;
import co.com.imdb.model.horario.Horario;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.usecase.horario.HoraioUseCase;
import co.com.imdb.usecase.pelicula.PeliculaUseCase;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = MainApplication.class)
class FunctionalConfigTest {

    private static final Faker FAKER = Faker.instance();

    private FunctionalConfig functionalConfig;
    @Autowired
    private WebTestClient    testClient;

    @MockBean
    private PeliculaUseCase peliculaUseCase;

    @MockBean
    private HoraioUseCase horaioUseCase;


    FunctionalConfigTest() {
    }

    private static
    Pelicula createDummyPelicula() {
        return Pelicula.builder()
                .nombre(FAKER.book().title())
                .idomdb(RandomStringUtils.random(4))
                .id(FAKER.number().numberBetween(0,100))
                .build();
    }
    private static
    List<Pelicula> createDummyPelicula(int n) {
        return Stream
                .generate(FunctionalConfigTest::createDummyPelicula)
                .limit(n)
                .collect(Collectors.toList());
    }

    private static
    PeliculaImdb createDummyPeliculaImbd() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return PeliculaImdb.builder()
                .Title(FAKER.book().title())
                .Actors(FAKER.harryPotter().character().toString())
                .imdbRating(String.valueOf(FAKER.number().numberBetween(0,10)))
                .Released(dateFormat.format(new Date()))
                .build();
    }


    private static
    Horario createDummyHorario() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return Horario.builder()
                .fechaFin("2022-01-25")
                .fechaFin("2022-04-25")
                .horaInicio("20:00")
                .idPelicula(2)
                .id(1)
                .build();
    }

    private static
    List<PeliculaImdb> createDummyPeliculaImbd(int n) {
        return Stream
                .generate(FunctionalConfigTest::createDummyPeliculaImbd)
                .limit(n)
                .collect(Collectors.toList());
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        functionalConfig = new FunctionalConfig(peliculaUseCase);

    }

    @Test
    void getPeliculaSort() {
        var peliculas = createDummyPeliculaImbd(5);
        peliculas.stream().sorted(Comparator.comparing(PeliculaImdb::getTitle));
        given(peliculaUseCase.getAllMoviesSorter(any())).willReturn(Mono.just(peliculas));
        testClient.get()
                .uri("/pelicula/order-by?sort=rating")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PeliculaImdb.class);

    }

    @Test
    void getAllPeliculasWithDetalle() {
        var peliculas = createDummyPeliculaImbd(5);
        peliculas.stream().sorted(Comparator.comparing(PeliculaImdb::getTitle));
        given(peliculaUseCase.getAllPeliculasWithDetalle()).willReturn(Mono.just(peliculas));
        testClient.get()
                .uri("/pelicula/allDetail")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PeliculaImdb.class);
    }

    @Test
    void updateHorarioPeliculaTest() {
        var horario = createDummyHorario();
        given(horaioUseCase.updateHorario(any())).willReturn(Mono.just(horario));
        testClient.put()
                .uri("/horario/updateHorarioPelicula")
                .body(Mono.just(horario), Horario.class)
                .exchange()
                .expectStatus().isNoContent();
    }
}