package com.oneHealth.blog.servicesImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.blog.entities.BlogReply;
import com.oneHealth.blog.repository.BlogReplyRepository;
import com.oneHealth.blog.services.BlogReplyService;


@Service
public class BlogReplyServiceImpl implements BlogReplyService {
	
	@Autowired
	BlogReplyRepository repository;

	/**
	 * Add a new BlogReply.
	 * 
	 * @param BlogReply The BlogReply to be added.
	 */
	@Override
	public void addBlogReply(BlogReply BlogReply) {
		repository.save(BlogReply); // Save the BlogReply object to the repository
	}
	
	/**
	 * Get all BlogReplys.
	 * 
	 * @return The list of BlogReplys.
	 */
	@Override
	public List<BlogReply> getBlogReplys() {
		return repository.findAll(); // Retrieve all BlogReplys from the repository
	}
	
	/**
	 * Delete a BlogReply by ID.
	 * 
	 * @param blogReply_Id The ID of the BlogReply to be deleted.
	 */
	@Override
	public void delete(int blogReply_Id) {
		repository.deleteById(blogReply_Id); // Delete the BlogReply from the repository based on ID
	}
	
	/**
	 * Get a BlogReply by ID.
	 * 
	 * @param blogReply_Id The ID of the BlogReply to retrieve.
	 * @return An optional containing the BlogReply, or an empty optional if not found.
	 */
	@Override
	public Optional<BlogReply> getBlogReply(int blogReply_Id) {
		return repository.findById(blogReply_Id); // Retrieve the BlogReply from the repository based on ID
	}
	
	/**
	 * Get a BlogReply by Comment ID.
	 * 
	 * @param blogReply_Id The ID of the BlogReply to retrieve.
	 * @return An optional containing the BlogReply, or an empty optional if not found.
	 */
	@Override
	public List<BlogReply> getBlogReplyByCommentId(int comment_Id) {
		return repository.findByCommentId(comment_Id); // Retrieve the BlogReply from the repository based on ID
	}
	
	/**
	 * Update the details of a BlogReply.
	 * 
	 * @param BlogReply The updated BlogReply object.
	 * @param did The ID of the BlogReply to be updated.
	 */
	@Override
	public void update(BlogReply BlogReply, int replyId) {
		System.out.println("inside update");
        // Set the editedDate to the current timestamp before calling the update method
		BlogReply.setEditedTimeStamp(new Timestamp(System.currentTimeMillis()));
		repository.update(
				BlogReply.getBody(),
				BlogReply.getEditedTimeStamp(),
				replyId
		);
	}
}
