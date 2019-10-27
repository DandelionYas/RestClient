package my.training;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;

@SpringBootApplication
public class RestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
                .setKeyStoreType("PKCS12")
                .loadTrustMaterial(ResourceUtils.getFile("classpath:ssl-truststore.truststore"),
                        "changeit".toCharArray())
                .build();

        HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Greeting response = restTemplate.getForObject(
                UriComponentsBuilder.fromUriString("https://localhost:8443/greeting")
                        .queryParam("name", "Yaser")
                        .build().toUriString()
                , Greeting.class);

        System.out.println(response);

        return restTemplate;
    }
}
