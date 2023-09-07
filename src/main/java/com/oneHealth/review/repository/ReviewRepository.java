package com.oneHealth.review.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.oneHealth.review.entities.Review;

/**
 * Repository interface for managing Review entities.
 * This interface provides methods to interact with the Review entity in the database.
 */
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /**
     * Updates a review in the database with the specified parameters.
     * @param type_id The type ID of the review.
     * @param review_by The user ID who posted the review.
     * @param type The type of review (e.g., product, service).
     * @param body The content of the review.
     * @param rating The rating given to the review.
     * @param edited_time The timestamp when the review was last edited.
     * @param review_id The unique identifier for the review to update.
     */
    @Modifying
    @Query(value = "UPDATE review "
            + "SET type_id = :type_id, "
            + "    review_by = :review_by, "
            + "    type = :type, "
            + "    body = :body, "
            + "    rating = :rating, "
            + "    edited_time = :edited_time "
            + "WHERE review_id = :review_id", nativeQuery = true)
    void update(
            @Param("type_id") int type_id,
            @Param("review_by") int review_by,
            @Param("type") String type,
            @Param("body") String body,
            @Param("rating") int rating,
            @Param("edited_time") Timestamp edited_time,
            @Param("review_id") int review_id
    );

    /**
     * Finds reviews by type and type ID.
     * @param type The type of review (e.g., product, service).
     * @param type_id The type ID of the review.
     * @return A list of reviews matching the specified type and type ID.
     */
    @Query(value = "SELECT * FROM review WHERE type = :type AND type_id = :type_id", nativeQuery = true)
    List<Review> findByTypeAndTypeId(
            @Param("type") String type,
            @Param("type_id") int type_id
    );

    /**
     * Finds all reviews by type.
     * @param type The type of review (e.g., product, service).
     * @return A list of reviews matching the specified type.
     */
    List<Review> findAllBytype(String type);

    /**
     * Finds a review by user ID, type, and type ID.
     * @param userId The user ID of the reviewer.
     * @param type The type of review (e.g., product, service).
     * @param typeId The type ID of the review.
     * @return The review matching the specified user ID, type, and type ID.
     */
    Review findByUserIdAndTypeAndTypeId(int userId, String type, int typeId);

    /**
     * Calculates the average rating for reviews of a specific type and type ID.
     * @param type The type of review (e.g., product, service).
     * @param type_id The type ID of the review.
     * @return The average rating for reviews of the specified type and type ID.
     */
    @Query(value = "SELECT AVG(rating) FROM review WHERE type = :type AND type_id = :type_id", nativeQuery = true)
    Double findAvgRatingByID(
            @Param("type") String type,
            @Param("type_id") int type_id
    );

    /**
     * Finds all reviews by user ID.
     * @param userId The user ID of the reviewer.
     * @return A list of reviews posted by the specified user ID.
     */
    List<Review> findAllByuserId(int userId);
}
