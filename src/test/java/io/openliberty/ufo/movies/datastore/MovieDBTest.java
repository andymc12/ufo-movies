package io.openliberty.ufo.movies.datastore;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}