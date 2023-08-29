package com.oneHealth.blog.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.blog.entities.BlogFavourite;
import com.oneHealth.blog.entities.BlogUser;
import com.oneHealth.blog.repository.BlogFavouriteRepository;
import com.oneHealth.blog.services.BlogFavouriteService;


@Service
public class BlogFavouriteServiceImpl implements BlogFavouriteService {
	
	@Autowired
	BlogFavouriteRepository repository;

	/**
	 * Add a new BlogFavourite.
	 * 
	 * @param BlogFavourite The BlogFavourite to be added.
	 */
	@Override
	public void addBlogFavourite(BlogFavourite BlogFavourite) {
		repository.save(BlogFavourite); // Save the BlogFavourite object to the repository
	}
	
	/**
	 * Get all BlogFavourites.
	 * 
	 * @return The list of BlogFavourites.
	 */
	@Override
	public List<BlogFavourite> getBlogFavourites() {
		return repository.findAll(); // Retrieve all BlogFavourites from the repository
	}
	
	/**
	 * Delete a BlogFavourite by ID.
	 * 
	 * @param blogFavourite_Id The ID of the BlogFavourite to be deleted.
	 */
	@Override
	public void delete(int blogFavourite_Id) {
		repository.deleteById(blogFavourite_Id); // Delete the BlogFavourite from the repository based on ID
	}
	
	/**
	 * Get a BlogFavourite by ID.
	 * 
	 * @param blogFavourite_Id The ID of the BlogFavourite to retrieve.
	 * @return An optional containing the BlogFavourite, or an empty optional if not found.
	 */
	@Override
	public Optional<BlogFavourite> getBlogFavourite(int blogFavourite_Id) {
		return repository.findById(blogFavourite_Id); // Retrieve the BlogFavourite from the repository based on ID
	}

	@Override
	public List<BlogFavourite> getBlogFavouriteByUID(int userId) {
		
		return repository.findByUserId(userId);
	}
	

	@Override
	public Long getFavouriteCount(int postId) {
		// TODO Auto-generated method stub
		return repository.countByPostId(postId);
	}

	@Override
	public List<Integer> getUserIdByPostId(int postId) {
		// TODO Auto-generated method stub
		return repository.findUserIdByPostId(postId);
	}
	
}
