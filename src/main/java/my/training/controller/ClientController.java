package my.training;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/d2c/v1")
public class ClientController {
    private final RestTemplate restTemplate;

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam String name) {
        Greeting response = restTemplate.getForObject(
                UriComponentsBuilder.fromUriString("https://localhost:8443/greeting")
                        .queryParam("name", name)
                        .build().toUriString()
                , Greeting.class);
        return response == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
}
