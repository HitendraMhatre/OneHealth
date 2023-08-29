package com.oneHealth.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.entities.BlogUser;

/**
 * Repository interface for managing BlogPosts.
 */
@Transactional
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    /**
     * Custom update method to modify BlogPost details.
     * @param blogId 
     * @param blogUser 
     * @param b 
     *
     * @param deptName     The new BlogPost name.
     * @param deptDesc     The new BlogPost description.
     * @param deptImgPath  The new image path for the BlogPost.
     * @param hospitalId   The new hospital ID associated with the BlogPost.
     */
    @Modifying
    @Query("UPDATE BlogPost b SET b.title = :title, b.content = :content,"
            + " b.imagePath = :image_path, b.departmentId = :department_id, b.approvalStatus = :approval_status, "
            + " b.approvedBy = :approved_by WHERE b.blogPostId = :blog_post_id")
    void update(
            @Param("title") String title,
            @Param("content") String content,
            @Param("image_path") String image_path,
            @Param("department_id") Integer department_id, 
            @Param("approval_status")boolean approval_status, 
            @Param("approved_by")BlogUser approved_by, 
            @Param("blog_post_id")Integer blog_post_id
    );
    
//    @Modifying
//    @Query(value =
//            "DELETE FROM blog_reply WHERE comment_id IN (SELECT c.comment_id FROM blog_comments c WHERE c.blog_post_id = :blog_post_id); "
//                    + "DELETE FROM blog_comments WHERE blog_post_id = :blog_post_id; "
//                    + "DELETE FROM blog_posts WHERE blog_post_id = :blog_post_id", nativeQuery = true)
//    void customDeleteById(@Param("blog_post_id") Integer blog_post_id);
    
    @Modifying
    @Query(value = "DELETE FROM blog_reply WHERE comment_id IN (SELECT c.comment_id FROM blog_comments c WHERE c.blog_post_id = :blog_post_id)", nativeQuery = true)
    void deleteRepliesByBlogPostId(@Param("blog_post_id") Integer blog_post_id);

    @Modifying
    @Query(value = "DELETE FROM blog_comments WHERE blog_post_id = :blog_post_id", nativeQuery = true)
    void deleteCommentsByBlogPostId(@Param("blog_post_id") Integer blog_post_id);
    
    @Modifying
    @Query(value = "DELETE FROM blog_posts WHERE blog_post_id = :blog_post_id", nativeQuery = true)
    void deletePostById(@Param("blog_post_id") Integer blog_post_id);
    
    @Modifying
    @Query(value = "DELETE FROM blog_favourite WHERE blog_post_id = :blog_post_id", nativeQuery = true)
    void deleteFavouriteByBlogPostId(@Param("blog_post_id") Integer blog_post_id);

    @Modifying
    @Query(value = "DELETE FROM blog_likes WHERE blog_post_id = :blog_post_id", nativeQuery = true)
    void deleteLikesByBlogPostId(@Param("blog_post_id") Integer blog_post_id);
}
