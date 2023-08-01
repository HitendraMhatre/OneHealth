package com.oneHealth.Hospital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating that a hospital resource is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HospitalNotFoundException extends RuntimeException {

    /**
     * Constructs an instance of HospitalNotFoundException with the specified error message.
     *
     * @param message The error message.
     */
    public HospitalNotFoundException(String message) {
        super(message);
    }
}
