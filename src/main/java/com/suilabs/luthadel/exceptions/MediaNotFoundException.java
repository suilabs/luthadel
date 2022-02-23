package com.suilabs.luthadel.exceptions;

public class MediaNotFoundException extends RuntimeException {
    public MediaNotFoundException(Long id) {
        super("Could not find media " + id);
    }
}
