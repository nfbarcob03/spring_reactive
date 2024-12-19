package com.fourthwall.kino.controller.mapper;

import com.fourthwall.kino.controller.model.MovieRepresentation;
import com.fourthwall.kino.controller.model.MovieRequest;
import com.fourthwall.kino.controller.model.ShowRepresentation;
import com.fourthwall.kino.controller.model.ShowRequest;
import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.domain.ShowDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogRepresentationMapper {
    CatalogRepresentationMapper INSTANCE = Mappers.getMapper(CatalogRepresentationMapper.class);

    MovieRepresentation toMovieRepresentation(MovieDto movie);
    ShowRepresentation toShowRepresentation(ShowDto showDto);
    List<ShowRepresentation> toShowRepresentations(List<ShowDto> shows);
    List<MovieRepresentation> toMovieRepresentations(List<MovieDto> movies);
    MovieDto fromMovieRequest(MovieRequest movieRequest);
    List<MovieDto> fromMovieRequest(List<MovieRequest> movieRequests);
    ShowDto fromShowRequest(ShowRequest showRequest);
    List<ShowDto> fromShowRequest(List<ShowRequest> showRequests);
}
