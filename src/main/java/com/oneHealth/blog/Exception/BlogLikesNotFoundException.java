package com.oneHealth.blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating that a BlogUser resource is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogLikesNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs an instance of BlogUserNotFoundException with the specified error message.
     *
     * @param message The error message.
     */
    public BlogLikesNotFoundException(String message) {
        super(message);
    }
}
