package co.com.imdb.jpa.horario;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;


@Data
@Table(name="horario")
@NoArgsConstructor
@ToString
@Getter
@Setter
public
class HorarioData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "horario_sequence")
    @SequenceGenerator(name="horario_sequence", sequenceName = "horario_sequence",
                    initialValue = 10,
                    allocationSize=200)
    private Integer id;
    private Integer idPelicula;
    private String  horaInicio;
    private String    fechaInicio;
    private String    fechaFin;
}
