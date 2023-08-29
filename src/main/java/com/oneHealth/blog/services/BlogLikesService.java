package com.oneHealth.blog.services;

import java.util.List;
import java.util.Optional;

import com.oneHealth.blog.entities.BlogComments;
import com.oneHealth.blog.entities.BlogLikes;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.entities.BlogReply;


public interface BlogLikesService {
	
	/**
	 * Add a new BlogLikes.
	 * 
	 * @param c The BlogLikes to be added.
	 */
	void addBlogLikes(BlogLikes c);
	
	/**
	 * Get all BlogLikess.
	 * 
	 * @return The list of BlogLikess.
	 */
	List<BlogLikes> getBlogLikess();
	
	/**
	 * Delete a BlogLikes by ID.
	 * 
	 * @param BlogLikes_Id The ID of the BlogLikes to be deleted.
	 */
	void delete(int BlogLikes_Id);
	
	/**
	 * Get a BlogLikes by ID.
	 * 
	 * @param BlogLikes_Id The ID of the BlogLikes to retrieve.
	 * @return An optional containing the BlogLikes, or an empty optional if not found.
	 */
	Optional<BlogLikes> getBlogLikes(int BlogLikes_Id);

	List<BlogLikes> getPostLikesByUID(int userId, int postId);
//	

	Long getLikesCount(boolean function, int postId);

	void updateBlogLikes(BlogLikes existingLike, BlogLikes blogLikes);

	BlogLikes getBlogLikesByPostIDAndUserId(Long blogPostId, Long likesId, int blogUserId, boolean flag,
			boolean function);
	
	List<Integer> getUserIdByPostId(int postId);
}
