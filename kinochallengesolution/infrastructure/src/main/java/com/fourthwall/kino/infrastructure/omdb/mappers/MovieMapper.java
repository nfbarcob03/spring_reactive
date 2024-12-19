package com.fourthwall.kino.infrastructure.omdb.mappers;

import com.fourthwall.kino.connector.OMDbMovieDto;
import com.fourthwall.kino.connector.RatingDto;
import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.MovieRateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(source = "title", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "movieId", target = "movieId")
    @Mapping(source = "released", target = "releaseDate")
    @Mapping(source = "runtime", target = "runtime")
    @Mapping(expression = "java(getImdbRate(omDbMovieDto))", target = "rating")
    MovieDto toMovie(OMDbMovieDto omDbMovieDto);

    @Mapping(source = "movieId", target = "movieId")
    @Mapping(expression = "java(getImdbRate(omDbMovieDto))", target = "externalRate")
    MovieRateDto toMovieRate(final OMDbMovieDto omDbMovieDto);

    default String getImdbRate(final OMDbMovieDto omDbMovieDto) {
        return omDbMovieDto.getRatings().stream()
                .filter(r -> r.getSource().contains("Internet"))
                .findAny().map(RatingDto::getValue)
                .orElse(null);
    }
}
