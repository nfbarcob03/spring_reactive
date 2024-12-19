package co.com.imdb.usecase.horario;

import co.com.imdb.model.horario.Horario;
import co.com.imdb.model.horario.HorarioDetalle;
import co.com.imdb.model.horario.HorarioRepository;
import co.com.imdb.model.pelicula.Pelicula;
import co.com.imdb.model.pelicula.PeliculaImdb;
import co.com.imdb.model.pelicula.PeliculaImdbRepository;
import co.com.imdb.model.pelicula.PeliculaRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HoraioUseCase {

    private final PeliculaRepository peliculaRepository;

    private final
    PeliculaImdbRepository peliculaImdbRepository;

    private final
    HorarioRepository horarioRepository;

    public
    HorarioDetalle getHorarioPelioculaByIdPelicula(Integer id) throws IOException {
        HorarioDetalle horarioDetalle = new HorarioDetalle();
        var horario = horarioRepository.getHorarioByIdPelicula(id);
        var pelicula = peliculaRepository.getPeliculaById(id);
        var peliculaImbd = peliculaImdbRepository.getDetallePelicula(pelicula.getIdomdb());
        horarioDetalle.setHorarios(horario);
        horarioDetalle.setGenero(peliculaImbd.getGenre());
        horarioDetalle.setNombre(pelicula.getNombre());
        horarioDetalle.setSinopsis(peliculaImbd.getPlot());
        horarioDetalle.setTiempoDuracion(peliculaImbd.getRuntime());
        return horarioDetalle;
    }

    public
    List<HorarioDetalle> getPeliculasDisponiblesHorario(){
        var peliculas = peliculaRepository.getAllPeliculas();
        return peliculas.stream().map(pelicula->
                                      {
                                          try {
                                              return getHorarioPelioculaByIdPelicula(pelicula.getId());
                                          }
                                          catch (IOException e) {
                                              throw new RuntimeException(e);
                                          }
                                      }).collect(Collectors.toList());
    }

    public void saveHorario(Horario horario){
        horarioRepository.saveHorarioPeliocula(horario);
    }

    public void updateHorario(Horario horario){
        horarioRepository.updateHorarioPeliocula(horario);
    }
}
