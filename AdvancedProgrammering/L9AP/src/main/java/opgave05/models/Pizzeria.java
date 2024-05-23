package opgave05.models;

import opgave05.models.pizzas.CheesePizza;
import opgave05.models.pizzas.GreekPizza;
import opgave05.models.pizzas.PepperoniPizza;
import opgave05.models.pizzas.Pizza;

public class Pizzeria {
    public Pizza orderPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if(type.equals("greek")) {
            pizza = new GreekPizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else {
            return null;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
