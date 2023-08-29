package com.oneHealth.blog.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.blog.entities.BlogFavourite;
import com.oneHealth.blog.entities.BlogUser;

/**
 * Repository interface for managing BlogCommentss.
 */
@Transactional
public interface BlogFavouriteRepository extends JpaRepository<BlogFavourite, Integer> {


    @Query(value = "SELECT COUNT(*) FROM blog_favourite WHERE blog_post_id = :blog_post_id", nativeQuery = true)
	Long countByPostId(
			@Param("blog_post_id") Integer blog_post_id
			);
 
    @Query(value = "SELECT * FROM blog_favourite WHERE blog_user_id = :blog_user_id", nativeQuery = true)
	List<BlogFavourite> findByUserId(
			@Param("blog_user_id") Integer blog_user_id
			);
    
    @Query(value = "SELECT blog_user_id FROM blog_favourite WHERE blog_post_id = :blog_post_id", nativeQuery = true)
	List<Integer> findUserIdByPostId(
			@Param("blog_post_id") Integer blog_post_id
			);
    
}

