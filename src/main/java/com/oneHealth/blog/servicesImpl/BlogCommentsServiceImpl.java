package com.oneHealth.blog.servicesImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.blog.entities.BlogComments;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.entities.BlogUser;
import com.oneHealth.blog.repository.BlogCommentsRepository;
import com.oneHealth.blog.services.BlogCommentsService;


@Service
public class BlogCommentsServiceImpl implements BlogCommentsService {
	
	@Autowired
	BlogCommentsRepository repository;

	/**
	 * Add a new BlogComments.
	 * 
	 * @param BlogComments The BlogComments to be added.
	 */
	@Override
	public void addBlogComments(BlogComments BlogComments) {
		repository.save(BlogComments); // Save the BlogComments object to the repository
	}
	
	/**
	 * Get all BlogCommentss.
	 * 
	 * @return The list of BlogCommentss.
	 */
	@Override
	public List<BlogComments> getBlogCommentss() {
		return repository.findAll(); // Retrieve all BlogCommentss from the repository
	}
	
	/**
	 * Delete a BlogComments by ID.
	 * 
	 * @param blogComments_Id The ID of the BlogComments to be deleted.
	 */
	@Override
	public void delete(int blogComments_Id) {
		repository.deleteRepliesByCommentId(blogComments_Id); // Delete the BlogComments from the repository based on ID
		repository.deleteCommentsByCommentId(blogComments_Id);
	}
	
	/**
	 * Get a BlogComments by ID.
	 * 
	 * @param blogComments_Id The ID of the BlogComments to retrieve.
	 * @return An optional containing the BlogComments, or an empty optional if not found.
	 */
	@Override
	public Optional<BlogComments> getBlogComments(int blogComments_Id) {
		return repository.findById(blogComments_Id); // Retrieve the BlogComments from the repository based on ID
	}
	
	/**
	 * Update the details of a BlogComments.
	 * 
	 * @param BlogComments The updated BlogComments object.
	 * @param did The ID of the BlogComments to be updated.
	 */
	@Override
	public void update(BlogComments BlogComments, int blogId) {
		System.out.println("inside update");
        // Set the editedDate to the current timestamp before calling the update method
		BlogComments.setEditedTimeStamp(new Timestamp(System.currentTimeMillis()));
		repository.update(
				BlogComments.getBody(),
				BlogComments.getEditedTimeStamp(),
				BlogComments.getRating(),
				blogId
		);
	}

	@Override
	public List<BlogComments> getBlogCommentsByPID(int postId) {
		
		return repository.findByPostId(postId);
	}
	
	 @Override
	    public boolean userAlreadyCommented(BlogUser user, BlogPost post) {
	        return repository.existsByUserAndPost(user, post);
	    }
	 
	 @Override
		public Double getPostAverageRating(int postId) {
			// TODO Auto-generated method stub
			return repository.getAverageRating(postId);
		}
}
