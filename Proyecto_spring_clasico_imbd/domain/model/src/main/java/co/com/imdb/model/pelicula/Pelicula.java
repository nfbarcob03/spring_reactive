package co.com.imdb.model.pelicula;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public
class Pelicula {
    private Integer id;
    private String idomdb;
    private String nombre;
}
