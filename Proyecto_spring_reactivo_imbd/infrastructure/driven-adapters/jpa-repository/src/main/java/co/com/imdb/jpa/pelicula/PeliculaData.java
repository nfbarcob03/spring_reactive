package co.com.imdb.jpa.pelicula;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
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
