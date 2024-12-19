package co.com.imdb.jpa.pelicula;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="pelicula")
@NoArgsConstructor
@ToString
@Getter
@Setter
public
class PeliculaData implements Serializable {
    @Id
    private Integer id;
    private String idomdb;
    private String nombre;
}
