package com.oneHealth.blog.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.blog.entities.BlogReply;

/**
 * Repository interface for managing BlogCommentss.
 */
@Transactional
public interface BlogReplyRepository extends JpaRepository<BlogReply, Integer> {

    
    @Modifying
    @Query(value = "UPDATE blog_reply SET body = :body, edited_time_stamp = :edited_time_stamp"
           + " WHERE reply_id = :reply_id", nativeQuery = true)
    void update(
    		    @Param("body") String body,
    		    @Param("edited_time_stamp") Timestamp edited_time_stamp,
    		    @Param("reply_id") Integer reply_id
    );
    
    @Query(value = "SELECT * FROM blog_reply WHERE comment_id = :comment_id ", nativeQuery = true)
	List<BlogReply> findByCommentId(
			@Param("comment_id") Integer comment_id
			);
    
   
}

