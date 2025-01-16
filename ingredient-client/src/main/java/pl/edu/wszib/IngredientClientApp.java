package pl.edu.wszib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableHystrix
public class IngredientClientApp{
    public static void main(String[] args) {
        SpringApplication.run(IngredientClientApp.class, args);
    }
}