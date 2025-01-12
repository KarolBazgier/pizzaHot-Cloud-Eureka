package pl.edu.wszib.ingredient_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Type;

@Configuration
public class DevelopmentConfig {


        @Bean
        public CommandLineRunner dataLoader(IngredientRepository repo) {
            return args -> {
                repo.save( new Ingredient("KUR", "Kurczak", Ingredient.Type.MEAT));
                repo.save(new Ingredient("WOŁ", "Wołowina", Ingredient.Type.MEAT));
                repo.save(new Ingredient("POM", "Pomidor", Ingredient.Type.VEGETABLES));

            };
        }
}
