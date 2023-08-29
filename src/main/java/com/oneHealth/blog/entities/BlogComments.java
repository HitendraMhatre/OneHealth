package com.oneHealth.blog.entities;

import java.sql.Timestamp;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "blog_comments")
public class BlogComments {
	 	public BlogComments() {
		super();
		// TODO Auto-generated constructor stub
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
	    @Column(name = "comment_id")
	    private Long commentId;

	 	@Column(columnDefinition = "TEXT", nullable = false)
	    private String body;

//		public Set<BlogReply> getReply() {
//			return reply;
//		}
//
//		public void setReply(Set<BlogReply> reply) {
//			this.reply = reply;
//		}
//
//		@JsonIgnoreProperties("reply") 
//		@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//	    private Set<BlogReply> reply;
		
		
	    @Column(nullable = false)
	    private int rating;
	    
	    public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public BlogComments(String body, int rating, Timestamp creationTimeStamp, Timestamp editedTimeStamp,
				BlogPost post, BlogUser user) {
			super();
			this.body = body;
			this.rating = rating;
			this.creationTimeStamp = creationTimeStamp;
			this.editedTimeStamp = editedTimeStamp;
			this.post = post;
			this.user = user;
		}

		@Temporal(TemporalType.TIMESTAMP)
	    @CreationTimestamp
	    @Column(name = "creation_time_stamp", nullable = false, updatable = false)
	    private Timestamp creationTimeStamp;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreationTimestamp
	    @Column(name = "edited_time_stamp", nullable = false, updatable = false)
	    private Timestamp editedTimeStamp;
	    
		public Timestamp getEditedTimeStamp() {
			return editedTimeStamp; 
		}

		public void setEditedTimeStamp(Timestamp editedTimeStamp) {
			this.editedTimeStamp = editedTimeStamp;
		}

		@Nonnull
	    @ManyToOne
	    @JsonIgnoreProperties("post")
	    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id", nullable = false)
	    private BlogPost post;

	    @Nonnull
	    @ManyToOne
	    @JoinColumn(name = "blog_user_id", referencedColumnName = "blog_user_id", nullable = false)
	    private BlogUser user;


		public Long getCommentId() {
			return commentId;
		}

		public void setCommentId(Long commentId) {
			this.commentId = commentId;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public Date getCreationTimeStamp() {
			return creationTimeStamp;
		}

		public void setCreationTimeStamp(Timestamp creationDate) {
			this.creationTimeStamp = creationDate;
		}

		public BlogUser getUser() {
			return user;
		}

		public BlogPost getPost() {
			return post;
		}


		public void setPost(BlogPost post) {
			this.post = post;
		}

		public void setUser(BlogUser user) {
			this.user = user;
		}
	    
}
