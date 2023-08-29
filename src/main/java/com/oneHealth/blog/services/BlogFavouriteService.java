package com.oneHealth.blog.services;

import java.util.List;
import java.util.Optional;

import com.oneHealth.blog.entities.BlogFavourite;
import com.oneHealth.blog.entities.BlogUser;


public interface BlogFavouriteService {
	
	/**
	 * Add a new BlogFavourite.
	 * 
	 * @param c The BlogFavourite to be added.
	 */
	void addBlogFavourite(BlogFavourite c);
	
	/**
	 * Get all BlogFavourites.
	 * 
	 * @return The list of BlogFavourites.
	 */
	List<BlogFavourite> getBlogFavourites();
	
	/**
	 * Delete a BlogFavourite by ID.
	 * 
	 * @param BlogFavourite_Id The ID of the BlogFavourite to be deleted.
	 */
	void delete(int BlogFavourite_Id);
	
	/**
	 * Get a BlogFavourite by ID.
	 * 
	 * @param BlogFavourite_Id The ID of the BlogFavourite to retrieve.
	 * @return An optional containing the BlogFavourite, or an empty optional if not found.
	 */
	Optional<BlogFavourite> getBlogFavourite(int BlogFavourite_Id);
	
	Long getFavouriteCount(int postId);

	List<BlogFavourite> getBlogFavouriteByUID(int userId);

	List<Integer> getUserIdByPostId(int postId);
}
