package com.oneHealth.blog.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.blog.entities.BlogComments;
import com.oneHealth.blog.entities.BlogLikes;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.entities.BlogReply;
import com.oneHealth.blog.repository.BlogLikesRepository;
import com.oneHealth.blog.services.BlogLikesService;


@Service
public class BlogLikesServiceImpl implements BlogLikesService {
	
	@Autowired
	BlogLikesRepository repository;

	/**
	 * Add a new BlogLikes.
	 * 
	 * @param BlogLikes The BlogLikes to be added.
	 */
	@Override
	public void addBlogLikes(BlogLikes BlogLikes) {
		repository.save(BlogLikes); // Save the BlogLikes object to the repository
	}
	
	/**
	 * Get all BlogLikess.
	 * 
	 * @return The list of BlogLikess.
	 */
	@Override
	public List<BlogLikes> getBlogLikess() {
		return repository.findAll(); // Retrieve all BlogLikess from the repository
	}
	
	/**
	 * Delete a BlogLikes by ID.
	 * 
	 * @param blogLikes_Id The ID of the BlogLikes to be deleted.
	 */
	@Override
	public void delete(int blogLikes_Id) {
		repository.deleteById(blogLikes_Id); // Delete the BlogLikes from the repository based on ID
	}
	
	/**
	 * Get a BlogLikes by ID.
	 * 
	 * @param blogLikes_Id The ID of the BlogLikes to retrieve.
	 * @return An optional containing the BlogLikes, or an empty optional if not found.
	 */
	@Override
	public Optional<BlogLikes> getBlogLikes(int blogLikes_Id) {
		return repository.findById(blogLikes_Id); // Retrieve the BlogLikes from the repository based on ID
	}


	@Override
	public Long getLikesCount( boolean function , int postId) {
		return repository.countLikesById(function,postId);
	}

	@Override
	public List<BlogLikes> getPostLikesByUID(int userId, int postId) {
		return repository.findByPostId(userId, postId);
	}

		@Override
	    public void updateBlogLikes(BlogLikes existingLike, BlogLikes blogLikes) {
	        // Implement logic to update the existing BlogLikes entry in your data source
	        // You can use the existingBlogLikes and updatedBlogLikes objects to perform the update
	    	repository.putBlogLikes(blogLikes.isFlag(),blogLikes.isFunction(),existingLike.getLikesId());
	    }

		@Override
		public BlogLikes getBlogLikesByPostIDAndUserId(Long blogPostId, Long likesId, int blogUserId, boolean flag,
				boolean function) {
			return repository.findBlogLikesByPostIdAndUserId(blogPostId,blogUserId);
		}

		@Override
		public List<Integer> getUserIdByPostId(int postId) {
			// TODO Auto-generated method stub
			return repository.findUserIdByPostId(postId);
	
		}
	
}
