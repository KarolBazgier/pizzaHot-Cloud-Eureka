package pl.edu.wszib.ingredient_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Iterable<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Ingredient> getIngredientById(@PathVariable String id){
        return ingredientRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void  updateIngredient(@PathVariable String id, @RequestBody Ingredient ingredient){
        if (ingredient.getId().equals(id)){
            ingredientRepository.save(ingredient);
        }else {
            throw new IllegalStateException("Produkt z id : " + id + " jest inny od: " + ingredient.getId() );
        }
    }

    @PostMapping
    public ResponseEntity<Ingredient> postNewIngredient(@RequestBody Ingredient ingredient){
        Ingredient saved = ingredientRepository.save(ingredient);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/ingredients/" + ingredient.getId()));
        return new ResponseEntity<>(saved,headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id){
        ingredientRepository.deleteById(id);
    }
}
