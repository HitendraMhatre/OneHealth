package com.oneHealth.review.service;

import java.util.List;
import java.util.Optional;

import com.oneHealth.review.entities.Review;
import com.oneHealth.review.exception.ResourceNotFoundException;

/**
 * The ReviewService interface defines the methods for managing Review entities.
 * This interface provides methods to interact with Review objects.
 */
public interface ReviewService {
	
	/**
	 * Add a new Review.
	 * 
	 * @param review The Review to be added.
	 * @throws ResourceNotFoundException if the resource is not found.
	 */
	void addReview(Review review) throws ResourceNotFoundException;
	
	/**
	 * Get all Reviews.
	 * 
	 * @return The list of Reviews.
	 */
	List<Review> getReviews();
	
	/**
	 * Delete a Review by ID.
	 * 
	 * @param reviewId The ID of the Review to be deleted.
	 */
	void delete(int reviewId);
	
	/**
	 * Get a Review by ID.
	 * 
	 * @param reviewId The ID of the Review to retrieve.
	 * @return An optional containing the Review, or an empty optional if not found.
	 */
	Optional<Review> getReview(int reviewId);

	/**
	 * Update a Review by ID.
	 * 
	 * @param review The updated Review.
	 * @param reviewId The ID of the Review to be updated.
	 */
	void update(Review review, int reviewId);

	/**
	 * Get Reviews by type and type ID.
	 * 
	 * @param type The type of Reviews to retrieve.
	 * @param typeId The ID of the type for which Reviews are to be retrieved.
	 * @return A list of Reviews matching the specified type and type ID.
	 */
	List<Review> getReviewBytypeId(String type, int typeId);

	/**
	 * Get Reviews by type.
	 * 
	 * @param type The type of Reviews to retrieve.
	 * @return A list of Reviews matching the specified type.
	 */
	List<Review> getReviewByType(String type);

	/**
	 * Get the average rating for Reviews of a specific type and type ID.
	 * 
	 * @param type The type of Reviews.
	 * @param typeId The ID of the type for which the average rating is calculated.
	 * @return The average rating for Reviews of the specified type and type ID.
	 */
	Double getAvgRatingByID(String type, int typeId);

	/**
	 * Get a Review by user ID, type, and type ID.
	 * 
	 * @param userId The ID of the user who posted the Review.
	 * @param type The type of the Review (e.g., product, service).
	 * @param typeId The ID of the type for which the Review was posted.
	 * @return The Review matching the specified user ID, type, and type ID.
	 */
	Review getReviewByUserAndType(int userId, String type, int typeId);

	/**
	 * Get Reviews by user ID.
	 * 
	 * @param userId The ID of the user whose Reviews are to be retrieved.
	 * @return A list of Reviews posted by the specified user ID.
	 */
	List<Review> getReviewByUserId(int userId);
}
