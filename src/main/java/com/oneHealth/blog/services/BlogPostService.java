package com.oneHealth.blog.services;

import java.util.List;
import java.util.Optional;

//import com.oneHealth.blog.DTO.BlogPostWithRatingDTO;
import com.oneHealth.blog.entities.BlogPost;


public interface BlogPostService {
	
	/**
	 * Add a new BlogPost.
	 * 
	 * @param c The BlogPost to be added.
	 */
	void addBlogPost(BlogPost c);
	
	/**
	 * Get all BlogPosts.
	 * 
	 * @return The list of BlogPosts.
	 */
	List<BlogPost> getBlogPosts();
	
	/**
	 * Delete a BlogPost by ID.
	 * 
	 * @param BlogPost_Id The ID of the BlogPost to be deleted.
	 */
	void delete(int BlogPost_Id);
	
	/**
	 * Get a BlogPost by ID.
	 * 
	 * @param BlogPost_Id The ID of the BlogPost to retrieve.
	 * @return An optional containing the BlogPost, or an empty optional if not found.
	 */
	Optional<BlogPost> getBlogPost(int BlogPost_Id);
	
	/**
	 * Update the details of a BlogPost.
	 * 
	 * @param BlogPost The updated BlogPost object.
	 * @param hid The ID of the BlogPost to be updated.
	 */
	void update(BlogPost BlogPost, int hid);
}
