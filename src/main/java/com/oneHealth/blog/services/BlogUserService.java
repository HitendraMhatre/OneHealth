package com.oneHealth.blog.services;

import java.util.List;
import java.util.Optional;

import com.oneHealth.blog.entities.BlogUser;


public interface BlogUserService {
	
	/**
	 * Add a new BlogUser.
	 * 
	 * @param c The BlogUser to be added.
	 */
	void addBlogUser(BlogUser c);
	
	/**
	 * Get all BlogUsers.
	 * 
	 * @return The list of BlogUsers.
	 */
	List<BlogUser> getBlogUsers();
	
	/**
	 * Delete a BlogUser by ID.
	 * 
	 * @param BlogUser_Id The ID of the BlogUser to be deleted.
	 */
	void delete(int BlogUser_Id);
	
	/**
	 * Get a BlogUser by ID.
	 * 
	 * @param BlogUser_Id The ID of the BlogUser to retrieve.
	 * @return An optional containing the BlogUser, or an empty optional if not found.
	 */
	Optional<BlogUser> getBlogUser(int BlogUser_Id);
	
	/**
	 * Update the details of a BlogUser.
	 * 
	 * @param BlogUser The updated BlogUser object.
	 * @param hid The ID of the BlogUser to be updated.
	 */
	void update(BlogUser BlogUser, int hid);
}
