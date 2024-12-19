package co.com.imdb.model.pelicula;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

public
class Pelicula {
    private Integer id;
    private String idomdb;
    private String nombre;
}
