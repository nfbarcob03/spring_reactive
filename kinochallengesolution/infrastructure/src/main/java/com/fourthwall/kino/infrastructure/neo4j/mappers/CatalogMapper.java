package com.fourthwall.kino.infrastructure.neo4j.mappers;

import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.MovieRateDto;
import com.fourthwall.kino.domain.ShowDto;
import com.fourthwall.kino.infrastructure.neo4j.entity.Movie;
import com.fourthwall.kino.infrastructure.neo4j.entity.MovieRate;
import com.fourthwall.kino.infrastructure.neo4j.entity.Show;
import com.fourthwall.kino.infrastructure.neo4j.repository.MovieRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {
    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    MovieDto toMovieDto(Movie movie);

    Movie toMovie(MovieDto movieDto);
    List<MovieDto> toMovieDtos(List<Movie> movies);

    @Mapping(source = "movieQueryResult.movie.id", target = "id")
    @Mapping(source = "movieQueryResult.movie.movieId", target = "movieId")
    @Mapping(source = "movieQueryResult.movie.name", target = "name")
    @Mapping(source = "movieQueryResult.avgRate", target = "avgRate")
    Movie fromMovieQueryResult(MovieRepository.MovieQueryResult movieQueryResult);

    @Mapping(source = "movieQueryResult.movie.id", target = "id")
    @Mapping(source = "movieQueryResult.movie.movieId", target = "movieId")
    @Mapping(source = "movieQueryResult.movie.name", target = "name")
    @Mapping(source = "movieQueryResult.avgRate", target = "avgRate")
    List<Movie> fromMovieQueryResult(List<MovieRepository.MovieQueryResult> movieQueryResult);

    Show toShow(ShowDto showDto);
    ShowDto toShowDto(Show show);

    MovieRate toMovieRate(MovieRateDto movieRateDto);
    MovieRateDto toMovieRateDto(MovieRate movieRateDto);

    List<ShowDto> toShowDtos(List<Show> shows);
    List<Show> toShows(List<ShowDto> showDtos);
}
