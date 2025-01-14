package com.example.movies.service;

import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.repository.MovieRepository;
import com.example.movies.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(String reviewBody, String imdbId) {
        Movie movie = movieRepository.findMovieByImdbId(imdbId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with imdbId: " + imdbId));

        Review review = new Review(reviewBody);
        review = reviewRepository.save(review);

        return review;
    }
}
