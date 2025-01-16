package pl.edu.wszib.restTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.edu.wszib.Ingredient;

import java.util.ArrayList;
import java.util.List;

@RestController
@Conditional(NotFeignAndNotWebClientCondition.class)
public class IngredientControllerResilience {

    private RestTemplate restTemplate;

    public IngredientControllerResilience(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //    @HystrixCommand(fallbackMethod = "getDefaultIngredients")
//    @GetMapping("/hystrix")
//    public Iterable<Ingredient> getAllIngredientsHystrix(){
//        ParameterizedTypeReference<List<Ingredient>> stringList = new ParameterizedTypeReference<List<Ingredient>>() {};
//        return restTemplate.exchange("http://ingredient-service/ingredients", HttpMethod.GET,
//                HttpEntity.EMPTY, stringList).getBody();
//    }
//    private Iterable<Ingredient> getDefaultIngredients(){
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient("CHE", "cheese",Ingredient.Type.CHEESE));
//        return ingredients;
//    }

    @CircuitBreaker(name = "ingredientService", fallbackMethod = "getDefaultIngredients")
    @GetMapping("/resilience")
    public Iterable<Ingredient> getAllIngredients() {
        ParameterizedTypeReference<List<Ingredient>> stringList = new ParameterizedTypeReference<>() {};
        return restTemplate.exchange("http://ingredient-service/ingredients", HttpMethod.GET,
                HttpEntity.EMPTY, stringList).getBody();
    }

    private Iterable<Ingredient> getDefaultIngredients(Exception e) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("CHE", "cheese", Ingredient.Type.CHEESE));
        return ingredients;
    }

}
