package com.cog.fundmatrix.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


public class NAVException {
	@ResponseStatus(HttpStatus.CONFLICT)
    public static class NAVAlreadyExistsException extends RuntimeException {
        public NAVAlreadyExistsException(String message) {
            super(message);
        }
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NAVNotFoundException extends RuntimeException {
        public NAVNotFoundException(String message) {
            super(message);
        }
    }
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public static class InvalidNAVStateTransitionException extends RuntimeException {
        public InvalidNAVStateTransitionException(String message) {
            super(message);
        }
    }

}
