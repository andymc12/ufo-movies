package io.openliberty.ufo.movies.datastore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieDBTest {
    private MovieDB db;

    @BeforeEach
    public void initDB() {
        db = new MovieDB();
        db.castAndCrewDB = new CastAndCrewDB();
        db.castAndCrewDB.loadInitialData();
        db.loadInitialData();
    }

    @Test
    public void testLoadInitialData() {
        assertEquals(4, db.allMovies().size());
    }

    @Test
    public void testUnknownMovieExceptions() {
        assertThrows(UnknownMovieException.class, () -> { db.getMovieById(Long.MIN_VALUE); });
        assertThrows(UnknownMovieException.class, () -> { db.getMovieById(9999l); });
    }
}