package co.com.imdb.consumer;

import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.model.pelicula.PeliculaImdbRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class RestConsumer implements PeliculaImdbRepository
{

    private final String url = "http://www.omdbapi.com/?apikey=9e160a8c&i=";

    private final OkHttpClient client;

    private final ObjectMapper mapper;

    public
    RestConsumer(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }


    // these methods are an example that illustrates the implementation of OKHTTP Client.
    // You should use the methods that you implement from the Gateway from the domain.

    public
    PeliculaImbdResponse getPeliculaImdb(String idImdb) throws IOException {

        Request request = new Request.Builder()
                .url(url+idImdb)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        return callAndMap(request, PeliculaImbdResponse.class);
    }
    @Bean
    public void init2(){
        System.out.println("inicialice el rest consumer");
    }


    private <T> T callAndMap(Request request, Class<T> clazz) throws IOException {
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return mapper.readValue(response.body().string(), clazz);
        }
        throw new IOException(response.toString());
    }

    @Override
    public
    PeliculaImdb getDetallePelicula(String id) throws IOException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);

        var data = getPeliculaImdb(id);
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
