package simulations;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicLoadTest extends Simulation {
    {
        // HTTP-Protokolls
        var httpProtocol = HttpDsl.http
                .baseUrl("http://localhost:8080")
                .acceptHeader("application/json");

        // 100 Anfragen /api/books
        var scn = scenario("LoadTest_Books_Endpoint")
                .repeat(100).on(
                        exec(
                                http("Get_Books_By_Keywords")
                                        .get("/api/books?keywords=")
                                        .check(status().is(200))
                        )
                );

        // Setup Simulation
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}

