package com.example.movies.service;

import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.repository.MovieRepository;
import com.example.movies.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(String reviewBody, String imdbId) {
        Optional<Movie> movieOptional = movieRepository.findMovieByImdbId(imdbId);

        Movie movie = movieOptional.orElseThrow(() -> new IllegalArgumentException("Movie not found with imdbId: " + imdbId));

        Review review = new Review(reviewBody);
        review = reviewRepository.save(review);

        // Ensure reviewIds is initialized to avoid NullPointerException
        List<Review> reviewIds = movie.getReviewIds();
        if (reviewIds == null) {
            reviewIds = new ArrayList<>();
            movie.setReviewIds(reviewIds);
        }

        reviewIds.add(review);
        movieRepository.save(movie);

        return review;
    }
}
