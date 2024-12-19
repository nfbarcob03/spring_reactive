package co.com.imdb.model.horario;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public
class HorarioDetalle {
    String        nombre;
    String        tiempoDuracion;
    String        genero;
    String        sinopsis;
    List<Horario> horarios;

}
