package io.openliberty.ufo.movies.rest;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.openliberty.ufo.movies.datastore.UnknownEntityException;

@Provider
public class UnknownEntityExceptionMapper implements ExceptionMapper<UnknownEntityException> {

    @Override
    public Response toResponse(UnknownEntityException exception) {
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(new ErrorResponse(404, exception.getMessage()));
        return Response.status(404).entity(result).build();
    }
}