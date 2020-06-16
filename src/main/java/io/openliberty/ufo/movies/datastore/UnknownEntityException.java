package io.openliberty.ufo.movies.datastore;

public class UnknownEntityException extends Exception {
    private static final long serialVersionUID = 1989297113806292793L;

    public UnknownEntityException() {
    }

    public UnknownEntityException(String message) {
        super(message);
    }
}