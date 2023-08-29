//package com.oneHealth.blog.entities;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//import org.hibernate.annotations.CreationTimestamp;
//
//import jakarta.annotation.Nonnull;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//
//@Entity
//@Table(name = "blog_rating")
//public class BlogRating {
//	 	@Id
//	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq_gen")
//	    @Column(name = "rating_id")
//	    private Long ratingId;
//
//	 	@Column(nullable = false)
//	    private int rating;
//
//	    @Temporal(TemporalType.TIMESTAMP)
//	    @CreationTimestamp
//	    @Column(name = "creation_time_stamp", nullable = false, updatable = false)
//	    private Timestamp creationTimeStamp;
//	    
//	    @Temporal(TemporalType.TIMESTAMP)
//	    @CreationTimestamp
//	    @Column(name = "edited_time_stamp", nullable = false, updatable = false)
//	    private Timestamp editedTimeStamp;
//	    
//		public Timestamp getEditedTimeStamp() {
//			return editedTimeStamp; 
//		}
//
//		public void setEditedTimeStamp(Timestamp editedTimeStamp) {
//			this.editedTimeStamp = editedTimeStamp;
//		}
//
//		@Nonnull
//	    @ManyToOne
//	    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id", nullable = false)
//	    private BlogPost post;
//
//	    @Nonnull
//	    @ManyToOne
//	    @JoinColumn(name = "blog_user_id", referencedColumnName = "blog_user_id", nullable = false)
//	    private BlogUser user;
//
//
//		public Long getRatingId() {
//			return ratingId;
//		}
//
//		public void setRatingId(Long commentId) {
//			this.ratingId = commentId;
//		}
//
//		public int getRating() {
//			return rating;
//		}
//
//		public void setRating(int rating) {
//	        if (rating >= 1 && rating <= 5) {
//	            this.rating = rating;
//	        } else {
//	            throw new IllegalArgumentException("Rating must be between 1 and 5.");
//	        }
//	    }
//
//		public Date getCreationTimeStamp() {
//			return creationTimeStamp;
//		}
//
//		public void setCreationTimeStamp(Timestamp creationDate) {
//			this.creationTimeStamp = creationDate;
//		}
//
//		public BlogUser getUser() {
//			return user;
//		}
//
//		public BlogPost getPost() {
//			return post;
//		}
//
//		public void setPost(BlogPost post) {
//			this.post = post;
//		}
//
//		public void setUser(BlogUser user) {
//			this.user = user;
//		}
//	    
//}
