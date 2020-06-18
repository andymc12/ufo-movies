package io.openliberty.ufo.movies.graphql;

import java.time.LocalDate;
import java.util.Collection;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.GraphQLException;
import org.eclipse.microprofile.graphql.Query;

import io.openliberty.ufo.movies.datastore.MovieDB;
import io.openliberty.ufo.movies.entity.Movie;

@GraphQLApi
public class MoviesApi {

    @Inject
    MovieDB db;

    @Query
    public Collection<Movie> allMovies() {
        return db.allMovies();
    }

    @Query
    public Collection<Movie> allMoviesStarring(String actorName) {
        return db.allMoviesStarring(actorName);
    }

    @Query
    public Collection<Movie> allMoviesDirectedBy(String directorName) {
        return db.allMoviesDirectedBy(directorName);
    }

    @Query
    public Collection<Movie> allMoviesReleasedBetween(LocalDate min, LocalDate max) throws GraphQLException {
        LocalDate x = min == null ? LocalDate.of(1900, 1, 1) : min;
        LocalDate y = max == null ? LocalDate.now() : max;
        return db.allMovies(m -> m.getReleaseDate().isAfter(x) && m.getReleaseDate().isBefore(y));
    }
}