package io.openliberty.ufo.movies.datastore;

public class UnknownMovieException extends UnknownEntityException {
    private static final long serialVersionUID = 378987113867892789L;

    public UnknownMovieException() {
    }

    public UnknownMovieException(String message) {
        super(message);
    }
}