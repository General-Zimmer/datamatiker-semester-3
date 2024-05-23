package opgave06;

import opgave06.models.Pizzeria;
import opgave06.models.factories.SimplePizzaFactory;

public class Opgave06 {
    public static void main(String[] args) {
        SimplePizzaFactory pizzaFactory = new SimplePizzaFactory();
        Pizzeria pizzeria = new Pizzeria(pizzaFactory);
        pizzeria.orderPizza("cheese", "NY");
    }

}
