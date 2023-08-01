package com.oneHealth.Hospital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating that a department resource is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {

    /**
     * Constructs an instance of DepartmentNotFoundException with the specified error message.
     *
     * @param message The error message.
     */
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
