package my.training.controller;

import lombok.AllArgsConstructor;
import my.training.dto.Greeting;
import my.training.service.GreetingService;
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
    private final GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greet(@RequestParam String name) {
        Greeting response = greetingService.greet(name);
        return response == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
}
