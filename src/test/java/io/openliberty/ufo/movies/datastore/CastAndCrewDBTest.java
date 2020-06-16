package io.openliberty.ufo.movies.datastore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.openliberty.ufo.movies.entity.Location;
import io.openliberty.ufo.movies.entity.Person;

public class CastAndCrewDBTest {
    CastAndCrewDB db;

    @BeforeEach
    public void initDB() {
        db = new CastAndCrewDB();
        db.loadInitialData();
    }

    @Test
    public void testLoadInitialData() {
        Person p = db.getDirectorByName("Roland Emmerich");
        assertEquals("Roland Emmerich", p.getName());
        assertEquals(new Location("Stuttgart", "Baden-Wurttemberg", "Germany"), p.getBirthplace());

        p = db.getActorByName("Jeff Goldblum");
        assertEquals("Jeff Goldblum", p.getName());
        assertEquals(new Location("Pittsburgh", "Pennsylvania", "USA"), p.getBirthplace());
    }

    @Test
    public void testGetActorsFrom() {
        List<Person> actors = db.getActorsFrom(new Location(null, "Pennsylvania", "USA"));
        assertEquals(2, actors.size());
        Person actor = actors.get(0);
        assertTrue(actor.getName().equals("Jeff Goldblum") || actor.getName().equals("Will Smith"));
        actor = actors.get(1);
        assertTrue(actor.getName().equals("Jeff Goldblum") || actor.getName().equals("Will Smith"));
        assertFalse(actors.get(0).getName().equals(actor.getName()));

    }
}