package com.oneHealth.blog.entities;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "blog_Reply")
public class BlogReply {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq_gen")
	    @Column(name = "reply_id")
	    private Long replyId;

	 	@Column(columnDefinition = "TEXT", nullable = false)
	    private String body;

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

		@ManyToOne
	    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false)
	    private BlogComments comment;

	    @Nonnull
	    @ManyToOne
	    @JoinColumn(name = "blog_user_id", referencedColumnName = "blog_user_id", nullable = false)
	    private BlogUser user;


		public Long getReplyId() {
			return replyId;
		}

		public void setReplyId(Long commentId) {
			this.replyId = commentId;
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

		public BlogComments getComment() {
			return comment;
		}

		public void setComment(BlogComments comment) {
			this.comment = comment;
		}

		public void setUser(BlogUser user) {
			this.user = user;
		}
	    
}
