package com.example.movies.model;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MovieTest {

    @Test
    public void testMovieConstructorAndGettersSetters() {
        // Test no-args constructor
        Movie movieNoArgs = new Movie();
        assertNotNull(movieNoArgs);

        ObjectId id = new ObjectId();
        String imdbId = "tt1234567";
        String title = "The Shawshank Redemption";
        String releaseDate = "1994-10-14";
        String trailerLink = "http://example.com/trailer";
        String poster = "https://example.com/poster.jpg";
        List<String> genres = List.of("Drama", "Crime");
        List<String> backdrops = List.of("https://example.com/backdrop1.jpg", "https://example.com/backdrop2.jpg");
        List<Review> reviews = List.of();

        // Test the all-args constructor
        Movie movie = new Movie(id, imdbId, title, releaseDate, trailerLink, poster, genres, backdrops, reviews);

        // Assertions for all fields
        assertEquals(id, movie.getId());
        assertEquals(imdbId, movie.getImdbId());
        assertEquals(title, movie.getTitle());
        assertEquals(releaseDate, movie.getReleaseDate());
        assertEquals(trailerLink, movie.getTrailerLink());
        assertEquals(poster, movie.getPoster());
        assertEquals(genres, movie.getGenres());
        assertEquals(backdrops, movie.getBackdrops());
        assertEquals(reviews, movie.getReviewIds());


        // Test setters
        ObjectId newId = new ObjectId();
        movie.setId(newId);
        assertEquals(newId, movie.getId());

        String newImdbId = "tt9876543";
        movie.setImdbId(newImdbId);
        assertEquals(newImdbId, movie.getImdbId());

        String newTitle = "New Movie Title";
        movie.setTitle(newTitle);
        assertEquals(newTitle, movie.getTitle());

        String newReleaseDate = "2024-01-01";
        movie.setReleaseDate(newReleaseDate);
        assertEquals(newReleaseDate, movie.getReleaseDate());

        String newTrailerLink = "http://example.com/newtrailer";
        movie.setTrailerLink(newTrailerLink);
        assertEquals(newTrailerLink, movie.getTrailerLink());

        String newPoster = "https://example.com/newposter.jpg";
        movie.setPoster(newPoster);
        assertEquals(newPoster, movie.getPoster());

        List<String> newGenres = List.of("Action", "Sci-Fi");
        movie.setGenres(newGenres);
        assertEquals(newGenres, movie.getGenres());

        List<String> newBackdrops = List.of("https://example.com/newbackdrop1.jpg");
        movie.setBackdrops(newBackdrops);
        assertEquals(newBackdrops, movie.getBackdrops());

        List<Review> newReviews = List.of(new Review());
        movie.setReviewIds(newReviews);
        assertEquals(newReviews, movie.getReviewIds());
    }
}