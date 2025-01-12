package pl.edu.wszib.restTemplate;

import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import pl.edu.wszib.Ingredient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@Conditional(NotFeignAndNotWebClientCondition.class)
public class IngredientServiceClient {

    private final WebClient webClient;

    public IngredientServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://ingredient-service").build();
    }

    public Mono<Ingredient> getIngredientById(String ingredientId) {
        return webClient.get()
                .uri("/ingredients/{id}", ingredientId)
                .retrieve()
                .bodyToMono(Ingredient.class);
    }

    public Mono<List<Ingredient>> getAllIngredients() {
        return webClient.get()
                .uri("/ingredients")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Ingredient>>() {});
    }
}