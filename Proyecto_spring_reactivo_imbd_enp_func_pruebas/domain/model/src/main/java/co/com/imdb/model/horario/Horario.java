package co.com.imdb.model.horario;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public
class Horario {
    private Integer id;
    private Integer idPelicula;
    private String  horaInicio;
    private String    fechaInicio;
    private String    fechaFin;
}
