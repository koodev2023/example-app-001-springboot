package com.example.movies.service;

import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.repository.MovieRepository;
import com.example.movies.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void testCreateReview_Success() {
        String reviewBody = "Great movie!";
        String imdbId = "tt1234567";

        Movie movie = new Movie(); // Create a mock movie object
        movie.setImdbId(imdbId);
        when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(movie));


        Review reviewToSave = new Review(reviewBody);  // The review *before* it's saved
        Review savedReview = new Review(reviewBody);   // The review *after* it's saved (could have an ID, etc.)
        when(reviewRepository.save(reviewToSave)).thenReturn(savedReview);

        Review createdReview = reviewService.createReview(reviewBody, imdbId);

        assertEquals(savedReview.getBody(), createdReview.getBody()); // Verify the returned review
    }


    @Test
    public void testCreateReview_MovieNotFound() {
        String reviewBody = "Great movie!";
        String imdbId = "tt1234567";

        when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> reviewService.createReview(reviewBody, imdbId));
    }
}