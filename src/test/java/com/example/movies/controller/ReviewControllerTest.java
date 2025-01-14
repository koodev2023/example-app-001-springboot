package com.example.movies.controller;

import com.example.movies.model.Review;
import com.example.movies.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    void testCreateReview() {
        // Arrange: Prepare test data and mock service response
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "Excellent movie!");
        payload.put("imdbId", "tt1234567");

        Review mockReview = new Review("Excellent movie!");
        when(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId"))).thenReturn(mockReview);


        // Act: Call the controller method
        ResponseEntity<Review> response = reviewController.createReview(payload);

        // Assert: Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockReview, response.getBody());
    }

    @Test
    void testCreateReview_ExceptionHandling() {
        // Arrange
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "Test review");
        payload.put("imdbId", "invalidImdbId");

        when(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")))
                .thenThrow(new IllegalArgumentException("Movie not found"));

        // Act
        ResponseEntity<Review> response = reviewController.createReview(payload);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}
