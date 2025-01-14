package com.example.movies.controller;


import com.example.movies.model.Movie;
import com.example.movies.service.MovieService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    void testGetAllMovies() {
        List<Movie> expectedMovies = Arrays.asList(
                new Movie(new ObjectId(), "tt1234567", "Movie 1", "2023", "trailerLink1", "poster1", Arrays.asList("Genre 1"), Arrays.asList("backdrop1"), Collections.emptyList()),
                new Movie(new ObjectId(), "tt7654321", "Movie 2", "2022", "trailerLink2", "poster2", Arrays.asList("Genre 2"), Arrays.asList("backdrop2"), Collections.emptyList())
        );
        when(movieService.allMovies()).thenReturn(expectedMovies);

        ResponseEntity<List<Movie>> response = movieController.getAllMovies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMovies, response.getBody());
    }

    @Test
    void testGetMovie_Found() {
        String imdbId = "tt1234567";
        Movie expectedMovie = new Movie(new ObjectId(), imdbId, "Movie 1", "2023", "trailerLink1", "poster1", Arrays.asList("Genre 1"), Arrays.asList("backdrop1"), Collections.emptyList());
        when(movieService.oneMovie(imdbId)).thenReturn(Optional.of(expectedMovie));

        ResponseEntity<Movie> response = movieController.getMovie(imdbId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMovie, response.getBody());
    }

    @Test
    void testGetMovie_NotFound() {
        String imdbId = "tt1234567";
        when(movieService.oneMovie(imdbId)).thenReturn(Optional.empty());

        ResponseEntity<Movie> response = movieController.getMovie(imdbId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}