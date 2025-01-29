# Catalog & Order Microservices

## Applikation starten

### Manuelle Ausführung

1. **Docker starten** und im Terminal folgenden Befehl ausführen:
   ```sh
   docker run -p5432:5432 -e POSTGRES_USER=catalog -e POSTGRES_PASSWORD=catalog -d postgres
   ```
2. **CatalogApplication starten**
3. **OrderApplication starten**
4. Bücher anzeigen: [`http://localhost:8080/api/books`](http://localhost:8080/api/books)
5. Buchsuche: [`http://localhost:8081/`](http://localhost:8081/)

### Mit Docker Compose

1. **Im Terminal zum Order-Verzeichnis navigieren**
2. **Docker Compose starten**
   ```sh
   docker compose up
   ```
3. Bücher anzeigen: [`http://localhost:8080/api/books`](http://localhost:8080/api/books)
4. Buchsuche: [`http://localhost:8081/`](http://localhost:8081/)

## Tests

### Unit-Tests

- `BookRepositoryTest` im Package `repository` im Catalog-Projekt ausführen

### Integrationstests

- `BookRestControllerSpringBootTest` und `BookRestControllerWebMvcTest` im Package `web` im Catalog-Projekt ausführen

### End-to-End-Test mit Playwright

1. **CatalogApplication und OrderApplication ausführen**
2. `CatalogOrderE2ETest` im Order-Projekt im Test-Ordner unter dem Package `playwright` ausführen

### Lasttest

- `BasicLoadTest` im Catalog-Projekt im Test-Ordner unter dem Package `simulations`

## Resilience Tests

### Retry Fallback für das Hinzufügen eines Buches testen

1. **CatalogApplication und OrderApplication starten**
2. **CatalogApplication stoppen**
3. **Versuchen, ein Buch in den Warenkorb hinzuzufügen:**
   ```sh
   http://localhost:8081/cart/add/9783161484100
   ```
4. **Erwartetes Verhalten:** Warenkorb zeigt das Buch ohne Titel und Autor an

### Resilience Retry Statistics anschauen

1. **CatalogApplication und OrderApplication starten**
2. **CatalogApplication stoppen**
3. **Versuchen, ein Buch in den Warenkorb hinzuzufügen:**
   ```sh
   http://localhost:8081/cart/add/9783161484100
   ```
4. **Retry-Statistiken abrufen:**
   ```sh
   http://localhost:8081/actuator/retries
   ```

