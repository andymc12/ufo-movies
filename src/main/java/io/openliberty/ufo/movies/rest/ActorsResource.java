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


@Path("/actors")
@Produces(MediaType.APPLICATION_JSON)
public class ActorsResource {

    @Inject
    CastAndCrewDB db;

    @GET
    public Collection<Person> allActors() {
        return db.getActors();
    }

    @POST
    public long createNewActor(Person actor) {
        db.getActors().add(actor);
        return actor.getId();
    }

    @GET
    @Path("{id}")
    public Person getActor(@PathParam("id") long id) throws UnknownPersonException {
        return db.getActorById(id);
    }

    @GET
    @Path("name/{name}")
    public Person getActorByName(@PathParam("name") String name) throws UnknownPersonException {
        return db.getActorByName(name);
    }

    @GET
    @Path("birthplace/{country}/{state}/{city}")
    public Collection<Person> getActorsByBirthplace(@PathParam("country") String country,
                                                    @PathParam("state") String state,
                                                    @PathParam("city") String city)
        throws UnknownPersonException {
        return db.getActorByBirthplace(new Location("-".equals(city) ? null : city,
                                                    "-".equals(state) ? null : state,
                                                    "-".equals(country) ? null : country));
    }
}
