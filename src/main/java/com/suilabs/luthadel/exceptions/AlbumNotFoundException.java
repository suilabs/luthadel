package com.suilabs.luthadel.exceptions;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Long id) {
        super("Could not find album " + id);
    }
}
