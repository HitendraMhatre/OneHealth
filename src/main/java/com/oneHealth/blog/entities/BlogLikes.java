package com.oneHealth.blog.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "blog_likes")
public class BlogLikes {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "likes_seq_gen")
	    @Column(name = "likes_id")
	    private Long likesId;
	 	
		@Nonnull
	    @ManyToOne
	    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id", nullable = false)
	    private BlogPost post;

//	 	@Column(name = "type")
//	    private String type;
//	 	
//	 	@Column(name = "type_id")
//		private Long typeId;
	 	
	 	@Column(name = "flag")
	 	private boolean flag;
	 	
	 	@Column(name = "function")
	 	private boolean function;
	 	 
	    @Nonnull
	    @ManyToOne
	    @JoinColumn(name = "blog_user_id", referencedColumnName = "blog_user_id", nullable = false)
	    private BlogUser user;

		public BlogLikes(BlogPost post, boolean flag, boolean function, BlogUser user) {
			super();
			this.post = post;
			this.flag = flag;
			this.function = function;
			this.user = user;
		}

		public Long getLikesId() {
			return likesId;
		}

		public void setLikesId(Long likesId) {
			this.likesId = likesId;
		}

		public BlogPost getPost() {
			return post;
		}

		public void setPost(BlogPost post) {
			this.post = post;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public boolean isFunction() {
			return function;
		}

		public void setFunction(boolean function) {
			this.function = function;
		}

		public BlogUser getUser() {
			return user;
		}

		public void setUser(BlogUser user) {
			this.user = user;
		}

		public BlogLikes() {
			super();
		}

		
		
}
