package com.oneHealth.blog.servicesImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.oneHealth.blog.DTO.BlogPostWithRatingDTO;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.repository.BlogPostRepository;
import com.oneHealth.blog.services.BlogPostService;


@Service
public class BlogPostServiceImpl implements BlogPostService {
	
	@Autowired
	BlogPostRepository repository;

	/**
	 * Add a new BlogPost.
	 * 
	 * @param BlogPost The BlogPost to be added.
	 */
	@Override
	public void addBlogPost(BlogPost BlogPost) {
		repository.save(BlogPost); // Save the BlogPost object to the repository
	}
	
	/**
	 * Get all BlogPosts.
	 * 
	 * @return The list of BlogPosts.
	 */
	@Override
	public List<BlogPost> getBlogPosts() {
		return repository.findAll(); // Retrieve all BlogPosts from the repository
	}
	
	/**
	 * Delete a BlogPost by ID.
	 * 
	 * @param blogPost_Id The ID of the BlogPost to be deleted.
	 */
	@Override
	public void delete(int blog_post_Id) {
		repository.deleteRepliesByBlogPostId(blog_post_Id);
		repository.deleteCommentsByBlogPostId(blog_post_Id);
		repository.deleteFavouriteByBlogPostId(blog_post_Id);
		repository.deleteLikesByBlogPostId(blog_post_Id);
		repository.deletePostById(blog_post_Id);
	}
	
	/**
	 * Get a BlogPost by ID.
	 * 
	 * @param blogPost_Id The ID of the BlogPost to retrieve.
	 * @return An optional containing the BlogPost, or an empty optional if not found.
	 */
	@Override
	public Optional<BlogPost> getBlogPost(int blogPost_Id) {
		return repository.findById(blogPost_Id); // Retrieve the BlogPost from the repository based on ID
	}
	
	/**
	 * Update the details of a BlogPost.
	 * 
	 * @param BlogPost The updated BlogPost object.
	 * @param did The ID of the BlogPost to be updated.
	 */
	@Override
	public void update(BlogPost BlogPost, int blogId) {
		System.out.println("inside update");
		BlogPost.setBlogEditedDate(new Timestamp(System.currentTimeMillis()));
		repository.update(
				BlogPost.getTitle(),
				BlogPost.getContent(),
				BlogPost.getImagePath(),
				BlogPost.getDepartmentId(),
				BlogPost.getApprovalStatus(),
				BlogPost.getApprovedBy(),
				blogId
		);
	}
}
