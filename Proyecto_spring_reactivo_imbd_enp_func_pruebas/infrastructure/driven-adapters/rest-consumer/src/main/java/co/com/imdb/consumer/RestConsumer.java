package co.com.imdb.consumer;

import co.com.imdb.model.error.exception.BadRequestException;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.model.pelicula.PeliculaImdbRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class RestConsumer implements PeliculaImdbRepository
{

    private final String url = "http://www.omdbapi.com";

    private final
    WebClient client;

    private final ObjectMapper mapper;

    public
    RestConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
        this.client = WebClient.create(url);
    }


    // these methods are an example that illustrates the implementation of OKHTTP Client.
    // You should use the methods that you implement from the Gateway from the domain.

    public
    Mono<PeliculaImbdResponse> getPeliculaImdb(String idImdb) throws IOException {
        return client.get().uri("/?apikey=9e160a8c&i={id}",idImdb).header("Content-Type", "application/json")
                .accept(APPLICATION_JSON)
                .retrieve().bodyToMono(PeliculaImbdResponse.class);

    }



    @Override
    public
    Mono<PeliculaImdb> getDetallePelicula(String id) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        try {
            var dataMono = getPeliculaImdb(id).block();
            if (dataMono.getResponse().equals("False")) {
                throw new BadRequestException(id, "No se encontro la pelicula en IMBD");
            }
            return Mono.just(dataMono).map(RestConsumer::castPeliculaImdbData);
        }
        catch (Exception e) {
            throw new BadRequestException(id, e.getMessage());
        }
    }

    private static
    PeliculaImdb castPeliculaImdbData(PeliculaImbdResponse data){
        PeliculaImdb peliculaImdb = new PeliculaImdb();
        peliculaImdb.setTitle(data.getTitle());
        peliculaImdb.setYear(data.getYear());
        peliculaImdb.setRated(data.getRated());
        peliculaImdb.setReleased(data.getReleased());
        peliculaImdb.setRuntime(data.getRuntime());
        peliculaImdb.setGenre(data.getGenre());
        peliculaImdb.setDirector(data.getDirector());
        peliculaImdb.setDirector(data.getDirector());
        peliculaImdb.setActors(data.getActors());
        peliculaImdb.setPlot(data.getPlot());
        peliculaImdb.setCountry(data.getCountry());
        peliculaImdb.setLanguage(data.getLanguage());
        peliculaImdb.setAwards(data.getAwards());
        peliculaImdb.setImdbID(data.getImdbID());
        peliculaImdb.setPoster(data.getPoster());
        peliculaImdb.setRatings(data.getRatings().stream().map(rating->{
            PeliculaImdb.Rating rating1 = new PeliculaImdb.Rating();
            rating1.setSource(rating.getSource());
            rating1.setValue(rating.getValue());
            return rating1;
        }).collect(Collectors.toList()));
        peliculaImdb.setMetascore(data.getMetascore());
        peliculaImdb.setImdbRating(data.getImdbRating());
        peliculaImdb.setImdbVotes(data.getImdbVotes());
        peliculaImdb.setImdbID(data.getImdbID());
        peliculaImdb.setType(data.getType());
        peliculaImdb.setDvd(data.getDvd());
        peliculaImdb.setBoxOffice(data.getBoxOffice());
        peliculaImdb.setProduction(data.getProduction());
        peliculaImdb.setWebsite(data.getWebsite());
        return peliculaImdb;
    }
}
