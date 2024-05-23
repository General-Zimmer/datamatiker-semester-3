package opgave06.models.pizza;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    private String name;
    private String dough;
    private List<String> toppings = new ArrayList<>();

    public Pizza(String name, String dough, List<String> toppings) {
        this.name = name;
        this.dough = dough;
        this.toppings = toppings;
    }

    public void prepare() {
        System.out.println("Forbereder en " + name);
        System.out.println("Laven en " + dough + " bund");
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

