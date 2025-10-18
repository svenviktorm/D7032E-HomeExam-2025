package myOwnVersion.Cards;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import myOwnVersion.Cards.CardComponents.CardComponent;

public class Card {
    private final int id;
    private final Map<Class<? extends CardComponent>, CardComponent> components = new HashMap<>();



    public Card(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public <T extends CardComponent> void addComponent(T component) {
        components.put(component.getClass(), component);
    }

    public <T extends CardComponent> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.containsKey(componentClass));
    }

    public boolean hasComponent(Class<? extends CardComponent> componentClass) {
        return components.containsKey(componentClass);
    }
}
