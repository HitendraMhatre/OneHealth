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
@Table(name = "blog_favourite")
public class BlogFavourite {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favourite_seq_gen")
	    @Column(name = "favourite_id")
	    private Long favouriteId;
	 	
		@Nonnull
	    @ManyToOne
	    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id", nullable = false)
	    private BlogPost post;

	    @Nonnull
	    @ManyToOne
	    @JoinColumn(name = "blog_user_id", referencedColumnName = "blog_user_id", nullable = false)
	    private BlogUser user;

		public BlogPost getPost() {
			return post;
		}

		public void setPost(BlogPost post) {
			this.post = post;
		}

		public BlogUser getUser() {
			return user;
		}

		public void setUser(BlogUser user) {
			this.user = user;
		}

		public BlogFavourite(BlogPost post, BlogUser user) {
			super();
			this.post = post;
			this.user = user;
		}

		public BlogFavourite() {
			super();
		}
}
