package io.openliberty.ufo.movies.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.openliberty.ufo.movies.datastore.CastAndCrewDB;
import io.openliberty.ufo.movies.datastore.UnknownPersonException;
import io.openliberty.ufo.movies.entity.Location;
import io.openliberty.ufo.movies.entity.Person;


@Path("/directors")
@Produces(MediaType.APPLICATION_JSON)
public class DirectorsResource {

    @Inject
    CastAndCrewDB db;

    @GET
    public Collection<Person> allDirectors() {
        return db.getDirectors();
    }

    @POST
    public long createNewDirector(Person director) {
        db.getDirectors().add(director);
        return director.getId();
    }

    @GET
    @Path("{id}")
    public Person getDirector(@PathParam("id") long id) throws UnknownPersonException {
        return db.getDirectorById(id);
    }

    @GET
    @Path("name/{name}")
    public Person getDirectorByName(@PathParam("name") String name) throws UnknownPersonException {
        return db.getDirectorByName(name);
    }

    @GET
    @Path("birthplace/{country}/{state}/{city}")
    public Collection<Person> getDirectorsByBirthplace(@PathParam("country") String country,
                                                       @PathParam("state") String state,
                                                       @PathParam("city") String city) {
        return db.getDirectorsFrom(new Location("-".equals(city) ? null : city,
                                                "-".equals(state) ? null : state,
                                                "-".equals(country) ? null : country));
    }
}
