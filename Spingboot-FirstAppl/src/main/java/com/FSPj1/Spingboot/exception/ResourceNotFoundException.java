package com.FSPj1.Spingboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)       //we used ResponseStatus so that API will return not found statement to client
public class ResourceNotFoundException extends RuntimeException{
    public static final long serialVersionUID =1L;

    public ResourceNotFoundException(String message){
        super(message);
    }
}
