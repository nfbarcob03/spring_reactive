package com.fourthwall.kino.configuration;

import com.fourthwall.kino.connector.OMDbConnector;
import com.fourthwall.kino.domain.MovieDB;
import com.fourthwall.kino.infrastructure.omdb.OmdbRepository;
import com.fourthwall.kino.ports.api.CinemaServicePort;
import com.fourthwall.kino.ports.spi.CinemaPersistencePort;
import com.fourthwall.kino.service.CinemaServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KinoConfiguration {
    @Value("${app.omdb.apiKey}")
    String omdbApiKey;

    @Bean
    public OMDbConnector omDbConnector() {
        return new OMDbConnector(omdbApiKey);
    }

    @Bean
    public MovieDB movieDB() {
        return new OmdbRepository(omDbConnector());
    }

    @Bean
    public CinemaServicePort cinemaServicePort(final CinemaPersistencePort cinemaPersistencePort) {
        return new CinemaServiceImpl(cinemaPersistencePort);
    }
}
