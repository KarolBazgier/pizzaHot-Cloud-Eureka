//package pl.edu.wszib.pizzas;
//
//import org.springframework.boot.actuate.info.Info;
//import org.springframework.boot.actuate.info.InfoContributor;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class PizzaInfoContributor implements InfoContributor {
//    private PizzaRepository pizzaRepository;
//
//    public PizzaInfoContributor(PizzaRepository pizzaRepository) {
//        this.pizzaRepository = pizzaRepository;
//    }
//
//    @Override
//    public void contribute(Info.Builder builder) {
//        long pizzaCount = pizzaRepository.count();
//        Map<String, Object> pizzaMap = new HashMap<String,Object>();
//        pizzaMap.put("count", pizzaCount);
//        builder.withDetail("pizza-stats", pizzaMap);
//    }
//}
