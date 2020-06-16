package io.openliberty.ufo.movies.datastore;

import static io.openliberty.ufo.movies.datastore.IdGenerator.generateID;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import io.openliberty.ufo.movies.entity.Location;
import io.openliberty.ufo.movies.entity.Person;

@ApplicationScoped
public class CastAndCrewDB {
    private List<Person> directors = new ArrayList<>();
    private List<Person> actors = new ArrayList<>();

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public void addActor(String name, Location birthplace) {
        actors.add(new Person(generateID(), name, birthplace));
    }

    public void addDirector(String name, Location birthplace) {
        directors.add(new Person(generateID(), name, birthplace));
    }

    public List<Person> getActorsFrom(Location birthplace) {
        return getPersons(actors, a -> a.getBirthplace().matches(birthplace));
    }

    public List<Person> getDirectorsFrom(Location birthplace) {
        return getPersons(directors, d -> d.getBirthplace().matches(birthplace));
    }

    public Person getDirectorByName(String name) {
        List<Person> list = getPersons(directors, d -> d.getName().equals(name));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Person getActorByName(String name) {
        List<Person> list = getPersons(actors, a -> a.getName().equals(name));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private List<Person> getPersons(List<Person> list, Predicate<? super Person> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    @PostConstruct
    void loadInitialData() {
        directors.add(new Person(generateID(), "Roland Emmerich",
            new Location("Stuttgart", "Baden-Wurttemberg", "Germany")));
        directors.add(new Person(generateID(), "Julien Temple",
            new Location("London", "England", "UK")));
        directors.add(new Person(generateID(), "Barry Sonnenfeld",
            new Location("New York City", "New York", "USA")));
        actors.add(new Person(generateID(), "Will Smith",
            new Location("Philadelphia", "Pennsylvania", "USA")));
        actors.add(new Person(generateID(), "Bill Pullman",
            new Location("Hornell", "New York", "USA")));
        actors.add(new Person(generateID(), "Jeff Goldblum",
            new Location("Pittsburgh", "Pennsylvania", "USA")));
        actors.add(new Person(generateID(), "Randy Quaid",
            new Location("Houston", "Texas", "USA")));
        actors.add(new Person(generateID(), "Geena Davis",
            new Location("Wareham", "Massachusetts", "USA")));
        actors.add(new Person(generateID(), "Jim Carey",
            new Location("Newmarket", "Ontario", "Canada")));
        actors.add(new Person(generateID(), "Tommy Lee Jones",
            new Location("San Saba", "Texas", "USA")));
        actors.add(new Person(generateID(), "Daphne Zuniga",
            new Location("San Francisco", "California", "USA")));
        actors.add(new Person(generateID(), "Joan Rivers",
            new Location("New York City", "New York", "USA")));
        actors.add(new Person(generateID(), "Rick Moranis",
            new Location("Toronto", "Ontario", "Canada")));
        actors.add(new Person(generateID(), "John Candy",
            new Location("Toronto", "Ontario", "Canada")));
        actors.add(new Person(generateID(), "Mel Brooks",
            new Location("New York City", "New York", "USA")));
    }
}