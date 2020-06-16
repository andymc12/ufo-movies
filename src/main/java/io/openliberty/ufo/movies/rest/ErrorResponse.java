package io.openliberty.ufo.movies.rest;

public class ErrorResponse {
    int errorCode;
    String message;

    ErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}