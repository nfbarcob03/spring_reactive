package co.com.imdb.jpa.horario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
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
