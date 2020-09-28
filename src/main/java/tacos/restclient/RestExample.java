package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
//import org.springframework.hateoas.MediaTypes;
//import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

//import java.net.URI;

@SpringBootConfiguration
@Slf4j
public class RestExample {
    public RestExample() {
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public Traverson traverson(){
//        return new Traverson(URI.create("hattp://localhost:8080/api"), MediaTypes.HAL_JSON);
//    }

}
