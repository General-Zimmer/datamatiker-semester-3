package opgave05.models.pizzas;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    private String name;
    private List<String> toppings = new ArrayList<>();

    public Pizza(String name, List<String> toppings) {
        this.name = name;
        this.toppings = toppings;
    }

    public void prepare() {
        System.out.println("Forbereder en " + name + " pizza");
        System.out.println("Ælter dejen.");
        System.out.println("Ruller dejen ud.");
        System.out.println("Tilføjer tomat sovs.");
        System.out.println("Tilføjer toppings.");
        for (String topping : toppings) {
            System.out.println("  " + topping);
        }
    };

    public void bake() {
        System.out.println("15 min. ved 350 grade.");
    };

    public void cut() {
        System.out.println("Skærer pizzaen i sliceses.");
    }
    public void box() {
        System.out.println("Putter pizzaen i boxen.");
    }
}
