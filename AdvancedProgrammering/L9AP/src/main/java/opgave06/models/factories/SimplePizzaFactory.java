package opgave06.models.factories;

import opgave06.models.pizza.*;

public class SimplePizzaFactory {
    public Pizza createPizza(String type, String style) {
        if (style.equals("NY")) {
            if (type.equals("cheese")) {
                return new NYStyleCheesePizza();
            } else if (type.equals("pepperoni")) {
                return new NYStylePepperoniPizza();
            } else {
                return null;
            }
        } else if (style.equals("Chicago")) {
            if (type.equals("cheese")) {
                return new ChicagoStyleCheesePizza();
            } else if (type.equals("pepperoni")) {
                return new ChicagoStylePepperoniPizza();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
