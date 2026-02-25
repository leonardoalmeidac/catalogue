package org.unir.msbookcatalogue.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String id) {
        super("Could not find book with id " + id);
    }
}
