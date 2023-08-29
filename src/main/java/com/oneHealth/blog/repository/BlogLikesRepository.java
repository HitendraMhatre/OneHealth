package com.oneHealth.blog.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.blog.entities.BlogComments;
import com.oneHealth.blog.entities.BlogLikes;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.entities.BlogReply;

/**
 * Repository interface for managing BlogLikes.
 */

@Transactional
public interface BlogLikesRepository extends JpaRepository<BlogLikes, Integer> {


    @Query(value = "SELECT COUNT(*) FROM blog_likes WHERE"
    		+ " function= :function AND flag= true AND blog_post_id = :blog_post_id ", nativeQuery = true)
	Long countLikesById(
			@Param("function") boolean function,
			@Param("blog_post_id") Integer blog_post_id
			);
 
    @Query(value = "SELECT * FROM blog_likes WHERE blog_user_id = :blog_user_id AND blog_post_id= :blog_post_id", nativeQuery = true)
	List<BlogLikes> findByPostId(
			@Param("blog_user_id") Integer blog_user_id,
			@Param("blog_post_id") Integer blog_post_id
			);

    @Query(value = "SELECT * FROM blog_likes WHERE blog_post_id= :blog_post_id AND blog_user_id=:blog_user_id", nativeQuery = true)
	BlogLikes findBlogLikesByPostIdAndUserId(
			@Param("blog_post_id")Long blog_post_id,
			@Param("blog_user_id") int blog_user_id);

	@Modifying
    @Query("UPDATE BlogLikes l SET l.flag = :flag, l.function = :function"
    		+ " WHERE l.likesId = :likesId")
	void putBlogLikes(boolean flag, boolean function, Long likesId);

    @Query(value = "SELECT blog_user_id FROM blog_likes WHERE blog_post_id = :blog_post_id", nativeQuery = true)
	List<Integer> findUserIdByPostId(
			@Param("blog_post_id") Integer blog_post_id
			);
    
    
}

