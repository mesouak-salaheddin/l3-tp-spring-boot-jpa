package library;

import fr.uga.l3miage.library.LibraryApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LibraryApplication.class)
class DemoApplicationTests {

    @SuppressWarnings("java:S2699")
    @Test
    void contextLoads() {

    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void homeResponse() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void apiResponse() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/api", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void authorsResponse() {
        var list = this.restTemplate.getForObject("/api/authors", List.class);
        assertThat(list).isNotEmpty();
    }
}
