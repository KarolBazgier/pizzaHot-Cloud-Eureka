package pl.edu.wszib.pizzas;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PizzaRepository extends PagingAndSortingRepository<Pizza, Long> {
    long count();
}
