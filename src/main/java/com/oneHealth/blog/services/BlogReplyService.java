package com.oneHealth.blog.services;

import java.util.List;
import java.util.Optional;

import com.oneHealth.blog.entities.BlogReply;


public interface BlogReplyService {
	
	/**
	 * Add a new BlogReply.
	 * 
	 * @param c The BlogReply to be added.
	 */
	void addBlogReply(BlogReply c);
	
	/**
	 * Get all BlogReplys.
	 * 
	 * @return The list of BlogReplys.
	 */
	List<BlogReply> getBlogReplys();
	
	/**
	 * Delete a BlogReply by ID.
	 * 
	 * @param BlogReply_Id The ID of the BlogReply to be deleted.
	 */
	void delete(int BlogReply_Id);
	
	/**
	 * Get a BlogReply by ID.
	 * 
	 * @param BlogReply_Id The ID of the BlogReply to retrieve.
	 * @return An optional containing the BlogReply, or an empty optional if not found.
	 */
	Optional<BlogReply> getBlogReply(int BlogReply_Id);
	
	/**
	 * Update the details of a BlogReply.
	 * 
	 * @param BlogReply The updated BlogReply object.
	 * @param hid The ID of the BlogReply to be updated.
	 */
	void update(BlogReply BlogReply, int hid);

	/**
	 * Get a BlogReply by Comment ID.
	 * 
	 * @param blogReply_Id The ID of the BlogReply to retrieve.
	 * @return An optional containing the BlogReply, or an empty optional if not found.
	 */
	List<BlogReply> getBlogReplyByCommentId(int comment_Id);
}
