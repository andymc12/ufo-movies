package io.openliberty.ufo.movies.entity;

public class Person {

    private long id;
    private String name;
    private Location birthplace;

    public Person() {}

    public Person(long id, String name, Location birthplace) {
        this.id = id;
        this.name = name;
        this.birthplace = birthplace;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getBirthplace() {
        return birthplace;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthplace(Location birthplace) {
        this.birthplace = birthplace;
    }
}