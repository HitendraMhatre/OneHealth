package com.oneHealth.blog.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.blog.entities.BlogUser;

/**
 * Repository interface for managing BlogUsers.
 */
@Transactional
public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {

    
    @Modifying
    @Query(value = "UPDATE blog_user SET user_name = :user_name, password = :password,"
            + " first_name = :first_name, last_name = :last_name, email_id = :email_id, user_type = :user_type,"
            + " mobile_num = :mobile_num WHERE blog_user_id = :blog_user_id", nativeQuery = true)
    void update(
    		 @Param("user_name") String user_name,
    		    @Param("password") String password,
    		    @Param("first_name") String first_name,
    		    @Param("last_name") String last_name,
    		    @Param("email_id") String email_id,
    		    @Param("user_type") String user_type,
    		    @Param("mobile_num") String mobile_num,
    		    @Param("blog_user_id") Integer blog_user_id
    );
    
   
}

