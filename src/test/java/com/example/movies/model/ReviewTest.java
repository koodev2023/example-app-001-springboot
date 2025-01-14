package com.example.movies.model;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    @Test
    public void testReviewConstructorsAndGettersSetters() {
        // Test no-args constructor
        Review reviewNoArgs = new Review();
        assertNotNull(reviewNoArgs);
        assertNull(reviewNoArgs.getId());
        assertNull(reviewNoArgs.getBody());

        // Test all-args (body only) constructor
        String body = "This is a great movie!";
        Review reviewBodyOnly = new Review(body);
        assertNotNull(reviewBodyOnly);
        assertNull(reviewBodyOnly.getId()); // Correct: id is null here
        assertEquals(body, reviewBodyOnly.getBody());

        // Test all-args (id and body) constructor
        ObjectId id = new ObjectId();
        Review reviewAllArgs = new Review(id, body);
        assertNotNull(reviewAllArgs);
        assertEquals(id, reviewAllArgs.getId());
        assertEquals(body, reviewAllArgs.getBody());


        // Test setter (using the body-only constructed object)
        String newBody = "This movie is even better on rewatch!";
        reviewBodyOnly.setBody(newBody);
        assertEquals(newBody, reviewBodyOnly.getBody());

        // Test setter for ID
        ObjectId newId = new ObjectId();
        reviewBodyOnly.setId(newId);
        assertEquals(newId, reviewBodyOnly.getId());

    }

    @Test
    public void testEqualsAndHashCode() {
        ObjectId id1 = new ObjectId();
        String body1 = "Test review";

        Review review1 = new Review(id1, body1);
        Review review2 = new Review(id1, body1);
        Review reviewNull = null;
        Object differentObject = new Object(); // Object of a different class

        // Identity check (this == o)
        assertEquals(review1, review1);

        // Null check (o == null)
        assertNotEquals(review1, reviewNull);

        // Class check (getClass() != o.getClass())
        assertNotEquals(review1, differentObject);

        // The rest of your existing equals tests (Symmetry, Transitivity, etc.)
        ObjectId id2 = new ObjectId();
        String body2 = "Another review";
        Review review3 = new Review(id2, body1); // Different ID
        Review review4 = new Review(id1, body2); // Different body
        Review review5 = new Review(null, body1); // Null ID
        Review review6 = new Review(null, body1); // Another Null ID
        Review review7 = new Review(id1, null); // Null body
        Review review8 = new Review(id1, null); // Another Null body

        // Symmetry
        assertEquals(review1, review2);
        assertEquals(review2, review1);
        assertEquals(review5, review6);
        assertEquals(review6, review5);
        assertEquals(review7, review8);
        assertEquals(review8, review7);

        // Transitivity
        Review review9 = new Review(id1, body1);
        assertEquals(review1, review2);
        assertEquals(review2, review9);
        assertEquals(review1, review9);

        // Consistency (multiple calls)
        assertEquals(review1.hashCode(), review2.hashCode());
        assertEquals(review1.hashCode(), review2.hashCode());

        // Inequality checks
        assertNotEquals(review1, review3);
        assertNotEquals(review1, review4);
        assertNotEquals(review1, review5);
        assertNotEquals(review1, review7);
        assertNotEquals(review5, review7);

        // HashCode consistency
        assertEquals(review1.hashCode(), review2.hashCode());
        assertEquals(review5.hashCode(), review6.hashCode());
        assertEquals(review7.hashCode(), review8.hashCode());
    }

    @Test
    public void testToString() {
        ObjectId id = new ObjectId();
        String body = "Test review";
        Review review = new Review(id, body);
        Review reviewNullId = new Review(null, body);
        Review reviewNullBody = new Review(id, null);

        assertNotNull(review.toString());
        assertTrue(review.toString().contains(id.toString()));
        assertTrue(review.toString().contains(body));

        assertNotNull(reviewNullId.toString());
        assertTrue(reviewNullId.toString().contains("null"));
        assertTrue(reviewNullId.toString().contains(body));

        assertNotNull(reviewNullBody.toString());
        assertTrue(reviewNullBody.toString().contains(id.toString()));
        assertTrue(reviewNullBody.toString().contains("null"));
    }

}