package pl.edu.wszib.restTemplate;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.Ingredient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
@Slf4j
@Conditional(NotFeignAndNotWebClientCondition.class)
public class IngredientController {


    private final IngredientServiceClient client;

    public IngredientController(IngredientServiceClient client) {
        this.client = client;
    }

    @GetMapping
    public Mono<String> ingredientList(Model model) {
        log.info("Pobrano składniki za pomocą WebClient !!!");
        return client.getAllIngredients()
                .map(ingredients -> {
                    model.addAttribute("ingredients", ingredients);
                    return "ingredientList";
                });
    }

    @GetMapping("/{id}")
    public Mono<String> ingredientDetailPage(@PathVariable("id") String id, Model model) {
        log.info("Pobrano składnik o id: " + id + " za pomocą WebClient !!!");
        return client.getIngredientById(id)
                .map(ingredientDetail -> {
                    model.addAttribute("ingredient", ingredientDetail);
                    return "ingredientDetail";
                });
    }
}
