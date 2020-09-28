package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tacos.Ingredient;

import java.util.List;


@Service
@Slf4j
public class TacoCloudClient {


    private RestTemplate rest;
//    private Traverson traverson;

    @Autowired
    public TacoCloudClient(RestTemplate rest) {
        this.rest = rest;
//        this.traverson = traverson;
    }


    public Ingredient getIngredientById(String ingredientId){
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    public List<Ingredient> getAllIngredients(){
        return rest.exchange("http://localhost:8080/ingredients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {}).getBody();
    }


    public void updateIngredient(Ingredient ingredient){
        rest.put("http://localhost:8080/ingredients/{id}",ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient){
        rest.delete("http://localhost:8080/ingredients/{id}",ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient){
        return rest.postForObject("http://localhost:8080/ingredients",ingredient, Ingredient.class);
    }
}
