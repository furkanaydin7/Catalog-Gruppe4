import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Lasttest extends Simulation {

  // HTTP-Protokoll konfigurieren
  val httpProtocol = http
    .baseUrl("http://localhost:8080") // Basis-URL
    .acceptHeader("application/json") // Header für JSON-Daten

  // Test-Szenario definieren
  val scn = scenario("Lasttest API")
    .repeat(100) { // Wiederhole 100 Anfragen
      exec(
        http("API Anfrage")
          .get("/api/books?keywords=")
          .check(status.is(200)) // Überprüfe, ob der Statuscode 200 OK ist
      )
    }

  // Setup für den Test
  setUp(
    scn.inject(atOnceUsers(10)) // Simuliere 10 Benutzer gleichzeitig
  ).protocols(httpProtocol)
}

