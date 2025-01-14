package com.example.movies.service;

import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testAllMovies() {
        List<Movie> expectedMovies = Arrays.asList(
                new Movie(new ObjectId(), "tt1234567", "Movie 1", "2023", "trailerLink1", "poster1", Arrays.asList("Genre 1"), Arrays.asList("backdrop1"), Collections.emptyList()),
                new Movie(new ObjectId(), "tt7654321", "Movie 2", "2022", "trailerLink2", "poster2", Arrays.asList("Genre 2"), Arrays.asList("backdrop2"), Collections.emptyList())
        );
        when(movieRepository.findAll()).thenReturn(expectedMovies);

        List<Movie> actualMovies = movieService.allMovies();

        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testOneMovie_Found() {
        String imdbId = "tt1234567";
        Movie expectedMovie = new Movie(new ObjectId(), imdbId, "Movie 1", "2023", "trailerLink1", "poster1", Arrays.asList("Genre 1"), Arrays.asList("backdrop1"), Collections.emptyList());
        when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(expectedMovie));

        Optional<Movie> actualMovie = movieService.oneMovie(imdbId);

        assertEquals(Optional.of(expectedMovie), actualMovie);
    }

    @Test
    void testOneMovie_NotFound() {
        String imdbId = "tt1234567";
        when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.empty());

        Optional<Movie> actualMovie = movieService.oneMovie(imdbId);

        assertEquals(Optional.empty(), actualMovie);
    }
}