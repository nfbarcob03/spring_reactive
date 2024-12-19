package com.fourthwall.kino.infrastructure.adapters;

import com.fourthwall.kino.domain.MovieDB;
import com.fourthwall.kino.domain.MovieDto;
import com.fourthwall.kino.infrastructure.neo4j.entity.Movie;
import com.fourthwall.kino.infrastructure.neo4j.entity.Show;
import com.fourthwall.kino.infrastructure.neo4j.mappers.CatalogMapper;
import com.fourthwall.kino.infrastructure.neo4j.repository.MovieRepository;
import com.fourthwall.kino.infrastructure.neo4j.repository.ShowRepository;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;

@ExtendWith(MockitoExtension.class)
public class CatalogAdapterTest {
    private static final String DEFAULT_MOVIE_ID = "tr5390033";

    @Mock
    private MovieDB movieDB;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private CatalogAdapter catalogAdapter;

    @Test
    void should_get_show_by_id() {
        var dummyShow = dummyShow(UUID.randomUUID().toString(), DEFAULT_MOVIE_ID);
        given(showRepository.findById(any())).willReturn(Optional.of(dummyShow));

        var result = catalogAdapter.getShowById(dummyShow.getId());
        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get().getId(), is(dummyShow.getId()));
    }

    @Test
    void should_update_show() {
        var showId = UUID.randomUUID().toString();
        var dummyShowOld = dummyShow(showId, DEFAULT_MOVIE_ID);
        var dummyShowNew = dummyShow(showId, DEFAULT_MOVIE_ID, LocalDateTime.now().plusHours(1),
                BigDecimal.valueOf(10D));
        var mId = UUID.randomUUID().toString();
        var movieOld = dummyMovie(mId, DEFAULT_MOVIE_ID,
                new HashSet<>(Collections.singletonList(dummyShowOld)));
        var movieNew = dummyMovie(mId, DEFAULT_MOVIE_ID,
                new HashSet<>(Collections.singletonList(dummyShowNew)));

        given(movieRepository.findByMovieId(dummyShowOld.getMovieId())).willReturn(Optional.of(movieOld));
        given(showRepository.findById(dummyShowOld.getId())).willReturn(Optional.of(dummyShowOld));
        given(movieRepository.save(any())).willReturn(movieNew);

        var updated = catalogAdapter.createOrUpdateShow(CatalogMapper.INSTANCE.toShowDto(dummyShowNew));

        assertThat(updated.getId(), is(mId));
        assertThat(updated.getMovieId(), is(DEFAULT_MOVIE_ID));
        assertThat(updated.getShows().size(), is(1));
        assertThat(updated.getShows().stream().findAny().get().getPrice(), equalTo(dummyShowNew.getPrice()));
        assertThat(updated.getShows().stream().findAny().get().getTime(), equalTo(dummyShowNew.getTime()));
    }

    @Test
    void should_create_movie() {
        var mId = UUID.randomUUID().toString();
        var movie = dummyMovie(mId, DEFAULT_MOVIE_ID, Collections.emptySet());

        given(movieRepository.save(any())).willReturn(movie);

        var created = catalogAdapter.addMovie(CatalogMapper.INSTANCE.toMovieDto(movie));

        assertThat(created.getId(), is(mId));
        assertThat(created.getMovieId(), is(DEFAULT_MOVIE_ID));
        assertThat(created.getShows().size(), is(0));
    }

    @Test
    void should_get_movie_info() {
        var localMovie = dummyMovie(
                UUID.randomUUID().toString(),
                DEFAULT_MOVIE_ID,
                Stream.generate(() -> dummyShow(UUID.randomUUID().toString(), DEFAULT_MOVIE_ID))
                        .limit(3).collect(Collectors.toSet())
        );
        var externalMovie = dummyExternalMovie(DEFAULT_MOVIE_ID);

        given(movieRepository.findByMovieId(DEFAULT_MOVIE_ID)).willReturn(Optional.of(localMovie));
        given(movieDB.getMovieInfo(DEFAULT_MOVIE_ID)).willReturn(Optional.of(externalMovie));
        var result = catalogAdapter.getMovie(DEFAULT_MOVIE_ID);

        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get().getName(), hasToString(externalMovie.getName()));
        assertThat(result.get().getDescription(), hasToString(externalMovie.getDescription()));
        assertThat(result.get().getShows().size(), is(localMovie.getShows().size()));
    }

    private static Show dummyShow(final String id, final String movieId, final LocalDateTime showTime, final BigDecimal price) {
        return new Show(
                id,
                movieId,
                showTime,
                price
        );
    }

    private static Show dummyShow(final String id, final String movieId) {
        return new Show(
                id,
                movieId,
                LocalDateTime.now(),
                BigDecimal.valueOf(RandomUtils.nextDouble(5D, 10D))
        );
    }

    private static Movie dummyMovie(final String id, final String movieId, final Set<Show> shows) {
        var faker = new Faker();
        return new Movie(
                id != null ? id : UUID.randomUUID().toString(),
                movieId,
                faker.pokemon().name(),
                null,
                null,
                null,
                null,
                Collections.emptySet(),
                shows
        );
    }

    private static MovieDto dummyExternalMovie(final String movieId) {
        var faker = new Faker();
        return new MovieDto(
                null,
                movieId,
                faker.pokemon().name(),
                faker.lorem().sentence(),
                LocalDate.now().minusYears(1),
                "6.8/10",
                "106 min",
                null,
                null
        );
    }
}
