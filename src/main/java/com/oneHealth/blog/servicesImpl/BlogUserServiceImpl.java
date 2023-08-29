package com.oneHealth.blog.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.blog.entities.BlogUser;
import com.oneHealth.blog.repository.BlogUserRepository;
import com.oneHealth.blog.services.BlogUserService;


@Service
public class BlogUserServiceImpl implements BlogUserService {
	
	@Autowired
	BlogUserRepository repository;

	/**
	 * Add a new BlogUser.
	 * 
	 * @param BlogUser The BlogUser to be added.
	 */
	@Override
	public void addBlogUser(BlogUser BlogUser) {
		repository.save(BlogUser); // Save the BlogUser object to the repository
	}
	
	/**
	 * Get all BlogUsers.
	 * 
	 * @return The list of BlogUsers.
	 */
	@Override
	public List<BlogUser> getBlogUsers() {
		return repository.findAll(); // Retrieve all BlogUsers from the repository
	}
	
	/**
	 * Delete a BlogUser by ID.
	 * 
	 * @param blogUser_Id The ID of the BlogUser to be deleted.
	 */
	@Override
	public void delete(int blogUser_Id) {
		repository.deleteById(blogUser_Id); // Delete the BlogUser from the repository based on ID
	}
	
	/**
	 * Get a BlogUser by ID.
	 * 
	 * @param blogUser_Id The ID of the BlogUser to retrieve.
	 * @return An optional containing the BlogUser, or an empty optional if not found.
	 */
	@Override
	public Optional<BlogUser> getBlogUser(int blogUser_Id) {
		return repository.findById(blogUser_Id); // Retrieve the BlogUser from the repository based on ID
	}
	
	/**
	 * Update the details of a BlogUser.
	 * 
	 * @param BlogUser The updated BlogUser object.
	 * @param did The ID of the BlogUser to be updated.
	 */
	@Override
	public void update(BlogUser BlogUser, int blogId) {
		System.out.println("inside update");
		repository.update(
				BlogUser.getUserName(),
				BlogUser.getPassword(),
				BlogUser.getFirstName(),
				BlogUser.getLastName(),
				BlogUser.getEmailId(),
				BlogUser.getUserType(),
				BlogUser.getMobileNum(),
				blogId
		);
	}
}
