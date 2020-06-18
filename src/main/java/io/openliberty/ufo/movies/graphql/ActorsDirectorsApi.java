package io.openliberty.ufo.movies.graphql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;

import io.openliberty.ufo.movies.datastore.CastAndCrewDB;
import io.openliberty.ufo.movies.datastore.UnknownPersonException;
import io.openliberty.ufo.movies.entity.Location;
import io.openliberty.ufo.movies.entity.Person;

@GraphQLApi
public class ActorsDirectorsApi {
    
    @Inject
    CastAndCrewDB db;

    @Query
    public Collection<Person> allActors() {
        return db.getActors();
    }

    @Query
    public Collection<Person> allDirectors() {
        return db.getDirectors();
    }

    @Query
    public Collection<Person> actorsFrom(@NonNull String country, String state, String city) {
        return db.getActorsFrom(new Location(city, state, country));
    }

    @Query
    public Collection<Person> directorsFrom(@NonNull String country, String state, String city) {
        return db.getDirectorsFrom(new Location(city, state, country));
    }

    @Query
    public Person getActorNamed(String name) throws UnknownPersonException {
        return db.getActorByName(name);
    }

    @Query
    public Person getDirectorNamed(String name) throws UnknownPersonException {
        return db.getDirectorByName(name);
    }

    @Query
    public Collection<Person> getPeopleNamed(String name) throws UnknownPersonException {
        List<Person> people = new ArrayList<>();
        try {
            people.add(db.getActorByName(name));
        } catch (UnknownPersonException ex) {
            //ignore
        }
        try {
            people.add(db.getDirectorByName(name));
        } catch (UnknownPersonException ex) {
            // ignore
        }
        if (people.isEmpty()) {
            throw new UnknownPersonException(name);
        }
        return people;
    }

    @Mutation
    public Person addActor(@NonNull String name, Location birthplace) {
        return db.addActor(name, birthplace);
    }

    @Mutation
    public Person addDirector(@NonNull String name, Location birthplace) {
        return db.addDirector(name, birthplace);
    }
}