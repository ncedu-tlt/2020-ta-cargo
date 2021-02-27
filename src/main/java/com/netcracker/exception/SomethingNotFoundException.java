package com.netcracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
    public class SomethingNotFoundException extends RuntimeException
    {
           public SomethingNotFoundException(String exception) {
            super(exception);
        }
    }
