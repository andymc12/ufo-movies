# UFO Movies Demo

## Introduction

This demo exists to show how you can create a [MicroProfile GraphQL](https://github.com/eclipse/microprofile-graphql) application to run in [Open Liberty](https://openliberty.io).

## MP Starter

One of the easiest ways to start a MicroProfile-based project is to use the MicroProfile Starter at:
https://start.microprofile.io

This project was initially generated by setting the following properties:
| Property             | Value          |
| --------             | -----          |
| groupId              | io.openliberty |
| artifactId           | ufo-movies     |
| MicroProfileVersion  | MP 3.3         |
| Java SE Version      | Java 8         |
| MicroProfile Runtime | Open Liberty   |

The Java version could also be Java 11 - your preference.  No need to select any of the "Examples for specifications", as GraphQL is not yet (as of this writing) included on the starter page.

Download this jar and extract it to an empty directory on your file system, and you should have something that closely resembles the [start branch](https://github.com/andymc12/ufo-movies/tree/start).

Notice that the starter generated a skeleton JAX-RS-based REST application for us. In some cases, we may be called upon to add a GraphQL endpoint to an existing RESTful application. We'll deal more with this later.

## Dependencies and server config

The MP starter will start us out with all the MP 3.3 features and dependencies. This could come in handy, but is really more than we need.  So, we'll slim it down to just what we need by adding the `mpGraphQL-1.0`, `cdi-2.0`, and `jaxrs-2.1` features (JAX-RS is only required if you want to compare/contrast GraphQL with REST). This also requires adding the following dependencies in the pom.xml file:
```
    <dependency>
      <groupId>org.eclipse.microprofile.graphql</groupId>
      <artifactId>microprofile-graphql-api</artifactId>
      <version>1.0.2</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>jakarta.enterprise</groupId>
      <artifactId>jakarta.enterprise.cdi-api</artifactId>
      <version>2.0.2</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>jakarta.ws.rs</groupId>
      <artifactId>jakarta.ws.rs-api</artifactId>
      <version>2.1.6</version>
    </dependency>
```

## The Entity Classes

Most microservice applications will have some sort of entity data. In this demo, we use entities like `Movie`, `Director`, `Actor`, etc.

This app will also need a way to get at the entity data. In real-world applications this might be by using a relational or a NoSQL database or some other sort of data store. In this example, we use an in-memory HashMap.

The [entityData branch](https://github.com/andymc12/ufo-movies/tree/entityData) adds the entity data classes and data access classes.

## A RESTful frontend?

Most microservices today are built with REST, so if you are migrating a RESTful app to GraphQL (or adding a GraphQL frontend to compliment your existing RESTful frontend), then it might be helpful to see how a REST app might use the entity classes and data access classes we've just created.

The [rest branch](https://github.com/andymc12/ufo-movies/tree/rest) replaces the built-in JAX-RS application and controller class (provided by the MicroProfile starter) with resource classes for querying and posting Movies (`MoviesResource`), Directors (`DirectorsResource`) and Actors (`ActorsResource`). It also includes an exception mapper that will map errors such as when a client requests an unknown movie to a 404 HTTP response with details in the JSON body.

## Now let's add the GraphQL frontend

We'll add POJO classes for the `MoviesApi` and we'll combine the APIs for actors and directors into `ActorsDirectorsApi`.  To make them known to the MP GraphQL runtime, we'll annotate the classes with `@GraphQLApi`, then annotate query methods with `@Query` and mutation methods with `@Mutation`.

We can also add descriptions in the schema to make it easier on the clients with `@Description`. Arguments that must not be null may be annotated with `@NonNull`.

The [graphql branch](https://github.com/andymc12/ufo-movies/tree/graphql) has many of these changes.

## Try it out!

Once we've got this coded up the way we'd like it, we can try it with [Open Liberty](https://openliberty.io/) and it's Maven plugin.  Download or clone this projects (the [master](https://github.com/andymc12/ufo-movies/tree/master) and [graphql](https://github.com/andymc12/ufo-movies/tree/graphql) branches contain the GraphQL APIs) and build it with:
`mvn clean package liberty:run`

That will build the app, download the latest released version of Open Liberty deploy the app and start the server. You should see a lot of output (especially the first time you run these targets).  The server is started when you see the following output:

```
[INFO] [AUDIT   ] CWWKE0001I: The server ufo-movies has been launched.
[INFO] [AUDIT   ] CWWKZ0058I: Monitoring dropins for applications.
[INFO] [AUDIT   ] CWWKT0016I: Web application available (default_host): http://localhost:9080/
[INFO] [AUDIT   ] CWWKZ0001I: Application ufo-movies started in 2.998 seconds.
[INFO] [AUDIT   ] CWWKF0012I: The server installed the following features: [cdi-2.0, jaxrs-2.1, jaxrsClient-2.1, jndi-1.0, jsonb-1.0, jsonp-1.1, mpConfig-1.4, mpGraphQL-1.0, servlet-4.0].
[INFO] [AUDIT   ] CWWKF0011I: The ufo-movies server is ready to run a smarter planet. The ufo-movies server started in 7.902 seconds.
```

Now you should be able test the GraphQL application using the [GraphiQL web interface](http://localhost:9080/graphiql.html) or the JAX-RS APIs from: http://localhost:9080/index.html

The GraphiQL interface has command completion and should help with constructing queries/mutations, but here are a few to get you started:

```
query {
  allActors {
    name,
    birthplace {
      city
      state
      country
    }
  }
}
```

```
query {
  directorsFrom(country: "UK") {
    name
  }
}
```

```
query {
  allMoviesReleasedBetween(min: "1990-01-01") {
    title
    releaseDate
    director {
      name
    }
  }
}
```

## That's all folks!

That's all for the demo - I hope you've enjoyed it!

If you are interested, please consider contributing to any of the following open source projects:
* MicroProfile GraphQL: https://github.com/eclipse/microprofile-graphql
* Open Liberty: https://github.com/OpenLiberty/open-liberty
* SmallRye GraphQL: https://github.com/smallrye/smallrye-graphql

Thanks!