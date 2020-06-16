package io.openliberty.ufo.movies.datastore;

public class UnknownPersonException extends UnknownEntityException {
    private static final long serialVersionUID = -985988912340129374L;

    public UnknownPersonException() {
    }

    public UnknownPersonException(String message) {
        super(message);
    }
}