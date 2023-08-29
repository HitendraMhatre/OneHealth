package com.oneHealth.blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating that a post is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogPostNotFoundException extends RuntimeException {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 3293704690193085575L;

		/**
	     * Constructs an instance of DepartmentNotFoundException with the specified error message.
	     *
	     * @param message The error message.
	     */
	    public BlogPostNotFoundException(String message) {
	        super(message);
	    }
	

}
