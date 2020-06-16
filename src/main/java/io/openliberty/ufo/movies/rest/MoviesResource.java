package io.openliberty.ufo.movies.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.openliberty.ufo.movies.datastore.MovieDB;
import io.openliberty.ufo.movies.datastore.UnknownMovieException;
import io.openliberty.ufo.movies.entity.Movie;


@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MoviesResource {

    @Inject
    MovieDB db;

    @GET
    public Collection<Movie> allMovies() {
        return db.allMovies();
    }

    @POST
    public long createNewMovie(Movie movie) {
        db.addMovie(movie);
        return movie.getId();
    }

    @GET
    @Path("{id}")
    public Movie getMovie(@PathParam("id") long id) throws UnknownMovieException {
        return db.getMovieById(id);
    }
}
