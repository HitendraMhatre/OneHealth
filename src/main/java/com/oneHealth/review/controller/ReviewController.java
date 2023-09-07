package com.oneHealth.review.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oneHealth.review.entities.Review;
import com.oneHealth.review.exception.ReviewNotFoundException;
import com.oneHealth.review.service.ReviewService;

/**
 * The ReviewController class handles the API endpoints related to Review operations.
 */
@RestController
@RequestMapping("/review/")
public class ReviewController {

    @Autowired
    ReviewService service;

    /**
     * Retrieves all the Reviews.
     *
     * @return ResponseEntity with the list of Review objects if they exist,
     *         or a no content response if no Reviews are found.
     */
    @GetMapping(value = "api/review")
    public ResponseEntity<List<Review>> showReviews() {
        List<Review> Reviews = service.getReviews();
        if (Reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(Reviews);
        }
    }

    /**
     * Retrieves a specific Review by its ID.
     *
     * @param reviewId The ID of the Review to retrieve.
     * @return ResponseEntity with the Review if found,
     *         or throws ReviewNotFoundException if the Review is not found.
     */
    @GetMapping(value = "api/reviewbyid/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable int reviewId) {
        try {
            Optional<Review> Review = service.getReview(reviewId);
            return Review.map(ResponseEntity::ok).orElseThrow(() ->
                    new ReviewNotFoundException("Review not found with ID: " + reviewId));
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Retrieves reviews by user ID.
     *
     * @param userId The ID of the user whose reviews are to be retrieved.
     * @return ResponseEntity with the list of reviews if found,
     *         or throws ReviewNotFoundException if no reviews are found.
     */
    @GetMapping(value = "api/reviewbyuserid/{userId}")
    public ResponseEntity<List<Review>> getReviewByUserId(@PathVariable int userId) {
        try {
            List<Review> reviews = service.getReviewByUserId(userId);
            if (reviews.isEmpty()) {
                throw new ReviewNotFoundException("Review not found with User ID: " + userId);
            }
            return ResponseEntity.ok(reviews);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves reviews by type and type ID.
     *
     * @param type The type of reviews to retrieve.
     * @param typeId The ID of the type for which reviews are to be retrieved.
     * @return ResponseEntity with the list of reviews if found,
     *         or throws ReviewNotFoundException if no reviews are found.
     */
    @GetMapping(value = "api/reviewbytypeandtypeid/{type}/{typeId}")
    public ResponseEntity<List<Review>> getReviewBytypeId(@PathVariable String type, @PathVariable int typeId) {
        try {
            List<Review> Reviews = service.getReviewBytypeId(type, typeId);
            if (Reviews.isEmpty()) {
                throw new ReviewNotFoundException("Review not found with type:" + type + " ID: " + typeId);
            }
            return ResponseEntity.ok(Reviews);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    /**
     * Retrieves reviews by type.
     *
     * @param type The type of reviews to retrieve.
     * @return ResponseEntity with the list of reviews if found,
     *         or throws ReviewNotFoundException if no reviews are found.
     */
    @GetMapping(value = "api/reviewbytype/{type}")
    public ResponseEntity<List<Review>> getReviewByType(@PathVariable String type) {
        try {
            List<Review> Reviews = service.getReviewByType(type);
            if (Reviews.isEmpty()) {
                throw new ReviewNotFoundException("Review not found with code: " + type);
            }
            return ResponseEntity.ok(Reviews);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    } 

    /**
     * Removes a Review by its ID.
     *
     * @param reviewId The ID of the Review to remove.
     * @return ResponseEntity with a success message if the Review is deleted successfully,
     *         or an error message if the Review deletion fails.
     */
    @DeleteMapping(value = "api/review/{reviewId}")
    public ResponseEntity<String> removeReview(@PathVariable int reviewId) {
        try {
            service.delete(reviewId);
            return ResponseEntity.ok("Review deleted successfully.");
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Review not found with ID: " + reviewId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Review: " + e.getMessage());
        }
    }

    /**
     * Adds a new Review or updates an existing one.
     *
     * @param Review The Review object to add or update.
     * @return ResponseEntity with a success message if the Review is added or updated successfully,
     *         or an error message if the Review operation fails.
     */
    @PostMapping(value = "api/review")
    public ResponseEntity<String> addOrUpdateReview(@RequestBody Review review) {
        try {
            // Get the user ID, type, and type ID from the incoming review
            int userId = review.getUserId();
            String type = review.getType();
            int typeId = review.getTypeId();

            // Check if a review already exists for the same user, type, and typeId
            Review existingReview = service.getReviewByUserAndType(userId, type, typeId);

            if (existingReview != null) {
                // Prompt the user to update their existing review
                service.update(review, existingReview.getReviewId());
                return ResponseEntity.ok("A review already exists for the same user, type, and typeId. Review details modified successfully.");
            } else {
                // Set the registration timestamp to the current time
                Timestamp currentTimestamp = Timestamp.from(Instant.now());
                review.setCreatedTime(currentTimestamp);
                review.setEditedTime(currentTimestamp);
                
                // Add the new review
                service.addReview(review);
                
                return ResponseEntity.ok("Review added successfully.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add or update review: " + e.getMessage());
        }
    }

    /**
     * Updates an existing Review by its ID.
     *
     * @param Review The updated Review object.
     * @param reviewId     The ID of the Review to update.
     * @return ResponseEntity with a success message if the Review is updated successfully,
     *         or an error message if the Review update fails.
     */
    @PutMapping(value = "api/review/{reviewId}")
    public ResponseEntity<String> updateReview(@RequestBody Review Review, @PathVariable int reviewId) {
        try {
            service.update(Review, reviewId);
            return ResponseEntity.ok("Review details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify Review details: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves the average rating for reviews of a specific type and type ID.
     *
     * @param type The type of reviews.
     * @param typeId The ID of the type for which the average rating is to be calculated.
     * @return ResponseEntity with the average rating if found,
     *         or throws ReviewNotFoundException if no reviews are found.
     */
    @GetMapping(value = "api/avgratingbytypeandid/{type}/{typeId}")
    public ResponseEntity<Double> getAvgRatingByID(@PathVariable String type, @PathVariable int typeId) {
        try {
            Double avgRating = service.getAvgRatingByID(type, typeId);
            
            if (avgRating != null) {
                return ResponseEntity.ok(avgRating);
            } else {
                throw new ReviewNotFoundException("Review not found with ID: " + typeId);
            }
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
