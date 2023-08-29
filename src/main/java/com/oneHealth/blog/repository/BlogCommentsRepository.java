package com.oneHealth.blog.repository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.blog.entities.BlogComments;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.entities.BlogUser;

/**
 * Repository interface for managing BlogCommentss.
 */
@Transactional
public interface BlogCommentsRepository extends JpaRepository<BlogComments, Integer> {

    
    @Modifying
    @Query(value = "UPDATE blog_comments SET body = :body, edited_time_stamp = :edited_time_stamp,rating= :rating"
           + " WHERE comment_id = :comment_id", nativeQuery = true)
    void update(
    		    @Param("body") String body,
    		    @Param("edited_time_stamp") Timestamp edited_time_stamp,
    		    @Param("rating") Integer rating,
    		    @Param("comment_id") Integer comment_id
    );
    
    boolean existsByUserAndPost(BlogUser user, BlogPost post);
    @Query(value = "SELECT AVG(rating) FROM blog_comments WHERE blog_post_id = :blog_post_id", nativeQuery = true)
    	Double getAverageRating(@Param("blog_post_id") Integer blogPostId);
   
  @Query(value = "SELECT * FROM blog_comments WHERE blog_post_id = :blog_post_id ", nativeQuery = true)
  List<BlogComments> findByPostId(
			@Param("blog_post_id") Integer blog_post_id
			);
  
  @Modifying
  @Query(value = "DELETE FROM blog_reply r WHERE r.comment_id = :comment_id", nativeQuery = true)
  void deleteRepliesByCommentId(@Param("comment_id") Integer comment_id);

  @Modifying
  @Query(value = "DELETE FROM blog_comments WHERE comment_id = :comment_id", nativeQuery = true)
  void deleteCommentsByCommentId(@Param("comment_id") Integer comment_id);
}

