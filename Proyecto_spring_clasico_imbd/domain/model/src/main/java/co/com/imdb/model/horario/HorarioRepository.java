package co.com.imdb.model.horario;

import co.com.imdb.model.pelicula.Pelicula;

import java.util.List;

public
interface HorarioRepository {
    List<Horario> getAllHorario();
    List<Horario> getHorarioByIdPelicula(Integer idPelicula);
    void updateHorarioPeliocula(Horario horario);

    void saveHorarioPeliocula(Horario horario);

}
