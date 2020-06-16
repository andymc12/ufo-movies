package io.openliberty.ufo.movies.datastore;

import java.util.concurrent.atomic.AtomicLong;

class IdGenerator {
    private static AtomicLong nextId = new AtomicLong(1000);

    static long generateID() {
        return nextId.getAndIncrement();
    }
}