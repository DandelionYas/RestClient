package my.training.service;

import lombok.RequiredArgsConstructor;
import my.training.dto.Greeting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GreetingService {
    private final RestTemplate restTemplate;

    @Value("${greeting.service.url}")
    private String greetingServiceUrl;

    public Greeting greet(String name) {
        return restTemplate.getForObject(
                UriComponentsBuilder.fromUriString(greetingServiceUrl + "/greeting")
                        .queryParam("name", name)
                        .build().toUriString(), Greeting.class);
    }
}
