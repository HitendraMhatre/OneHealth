package com.oneHealth.blog.services;

import java.util.List;
import java.util.Optional;

import com.oneHealth.blog.entities.BlogComments;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.entities.BlogUser;


public interface BlogCommentsService {
	
	/**
	 * Add a new BlogComments.
	 * 
	 * @param c The BlogComments to be added.
	 */
	void addBlogComments(BlogComments c);
	

    
    
	/**
	 * Get all BlogCommentss.
	 * 
	 * @return The list of BlogCommentss.
	 */
	List<BlogComments> getBlogCommentss();
	
	/**
	 * Delete a BlogComments by ID.
	 * 
	 * @param BlogComments_Id The ID of the BlogComments to be deleted.
	 */
	void delete(int BlogComments_Id);
	
	/**
	 * Get a BlogComments by ID.
	 * 
	 * @param BlogComments_Id The ID of the BlogComments to retrieve.
	 * @return An optional containing the BlogComments, or an empty optional if not found.
	 */
	Optional<BlogComments> getBlogComments(int BlogComments_Id);
	

	List<BlogComments> getBlogCommentsByPID(int postId);
	
	/**
	 * Update the details of a BlogComments.
	 * 
	 * @param BlogComments The updated BlogComments object.
	 * @param hid The ID of the BlogComments to be updated.
	 */
	void update(BlogComments BlogComments, int hid);
	
	
    /**
     * Check if a user has already commented on a specific blog post.
     *
     * @param user The user who wants to add a comment.
     * @param post The blog post to check against.
     * @return true if the user has already commented on the post, false otherwise.
     */
	
	public boolean userAlreadyCommented(BlogUser user, BlogPost post);
	
	Double getPostAverageRating(int postId);

}
