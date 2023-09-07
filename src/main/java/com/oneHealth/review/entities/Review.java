package com.oneHealth.review.entities;

import java.sql.Timestamp;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a review entity.
 * Author: Hitemdra Mhatre
 */
@Entity
@Table(name = "review")
public class Review {
    
    // Unique identifier for the review
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_gen")
    @Column(name = "review_id")
    private int reviewId;

    // Type of the review (e.g., product, service)
    @Nonnull
    @Column(name = "type")
    private String type;

    // Identifier for the type (e.g., product ID)
    @Column(name = "type_id")
    private int typeId;

    // User ID of the reviewer
    @Nonnull
    @Column(name = "review_by")
    private int userId;

    // Content of the review
    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    // Rating given to the review
    @Column(nullable = false)
    private int rating;

    // Timestamp when the review was created
    @Column(name = "created_time")
    private Timestamp createdTime;

    // Timestamp when the review was last edited
    @Column(name = "edited_time")
    private Timestamp editedTime;

    /**
     * Constructs a new Review object with specified attributes.
     * @param reviewId The unique identifier for the review.
     * @param type The type of review.
     * @param typeId The identifier for the type.
     * @param userId The user ID of the reviewer.
     * @param body The content of the review.
     * @param rating The rating given to the review.
     * @param createdTime The timestamp when the review was created.
     * @param editedTime The timestamp when the review was last edited.
     */
    public Review(int reviewId, String type, int typeId, int userId, String body, int rating, Timestamp createdTime,
                  Timestamp editedTime) {
        super();
        this.reviewId = reviewId;
        this.type = type;
        this.typeId = typeId;
        this.userId = userId;
        this.body = body;
        this.rating = rating;
        this.createdTime = createdTime;
        this.editedTime = editedTime;
    }

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getEditedTime() {
		return editedTime;
	}

	public void setEditedTime(Timestamp editedTime) {
		this.editedTime = editedTime;
	}

	 /**
     * Default constructor for the Review class.
     */
	public Review() {
		super();
	}
} 
   
