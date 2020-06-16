package io.openliberty.ufo.movies.datastore;

import static io.openliberty.ufo.movies.datastore.IdGenerator.generateID;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.openliberty.ufo.movies.entity.Movie;

@ApplicationScoped
public class MovieDB {
    private static final Logger LOG = Logger.getLogger(MovieDB.class.getName());

    @Inject
    CastAndCrewDB castAndCrewDB;

    private final Map<Long, Movie> allMovies = new HashMap<>();

    public Movie getMovieById(long id) throws UnknownMovieException {
        Movie m = allMovies.get(id);
        if (m == null) {
            throw new UnknownMovieException("Unknown movie ID: " + id);
        }
        return m;
    }

    public Collection<Movie> allMovies() {
        return allMovies.values();
    }

    public Collection<Movie> allMoviesDirectedBy(String directorName) {
        return allMovies(m -> m.getDirector().getName().equals(directorName));
    }

    public Collection<Movie> allMoviesStarring(String actorName) {
        return allMovies(m -> m.getActors().stream().anyMatch(a -> a.getName().equals(actorName)));
    }

    public Collection<Movie> allMovies(Predicate<? super Movie> predicate) {
        return allMovies.values().stream().filter(predicate).collect(Collectors.toList());
    }

    public void addMovie(Movie movie) {
        allMovies.put(movie.getId(), movie);
    }

    public Movie addMovie(String title) {
        Movie movie = new Movie();
        movie.setId(IdGenerator.generateID());
        movie.setTitle(title);
        allMovies.put(movie.getId(), movie);
        return movie;
    }

    @PostConstruct
    void loadInitialData() {
        try {
        Movie m = new Movie(generateID(), "Independence Day");
        m.setReleaseDate(LocalDate.of(1996, 7, 3));
        m.setDirector(castAndCrewDB.getDirectorByName("Roland Emmerich"));
        m.setActors(Arrays.asList(castAndCrewDB.getActorByName("Will Smith"),
                                  castAndCrewDB.getActorByName("Bill Pullman"),
                                  castAndCrewDB.getActorByName("Jeff Goldblum"),
                                  castAndCrewDB.getActorByName("Randy Quaid")));
        allMovies.put(m.getId(), m);

        m = new Movie(generateID(), "Men In Black");
        m.setReleaseDate(LocalDate.of(1997, 7, 2));
        m.setDirector(castAndCrewDB.getDirectorByName("Barry Sonnenfeld"));
        m.setActors(Arrays.asList(castAndCrewDB.getActorByName("Will Smith"),
                                  castAndCrewDB.getActorByName("Tommy Lee Jones")));
        allMovies.put(m.getId(), m);

        m = new Movie(generateID(), "Earth Girls Are Easy");
        m.setReleaseDate(LocalDate.of(1989, 5, 12));
        m.setDirector(castAndCrewDB.getDirectorByName("Julien Temple"));
        m.setActors(Arrays.asList(castAndCrewDB.getActorByName("Geena Davis"),
                                  castAndCrewDB.getActorByName("Jeff Goldblum"),
                                  castAndCrewDB.getActorByName("Jim Carey")));
        allMovies.put(m.getId(), m);

        m = new Movie(generateID(), "Spaceballs");
        m.setReleaseDate(LocalDate.of(1987, 6, 24));
        m.setDirector(castAndCrewDB.getDirectorByName("Mel Brooks"));
        m.setActors(Arrays.asList(castAndCrewDB.getActorByName("Mel Brooks"),
                                  castAndCrewDB.getActorByName("Bill Pullman"),
                                  castAndCrewDB.getActorByName("John Candy"),
                                  castAndCrewDB.getActorByName("Rick Moranis"),
                                  castAndCrewDB.getActorByName("Joan Rivers"),
                                  castAndCrewDB.getActorByName("Daphne Zuniga")));
        allMovies.put(m.getId(), m);
        } catch (Exception ex) {
            LOG.severe("Failed to initialize Movie database");
        }
    }
}