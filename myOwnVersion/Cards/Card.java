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
        // check if component which this component class already exists or a class which this component extends
        // exception for CardComponent interface itself
        if (CardComponent.class.isAssignableFrom(component.getClass())) {
            for (Class<? extends CardComponent> existingClass : components.keySet()) {
                if (existingClass.isAssignableFrom(component.getClass()) || component.getClass().isAssignableFrom(existingClass)) {
                    throw new IllegalArgumentException("Component of class " + component.getClass().getName() + " or its subclass/superclass already exists in the card.");
                }
            }
        }
        components.put(component.getClass(), component);
    }

    public <T extends CardComponent> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

    public boolean hasComponent(Class<? extends CardComponent> componentClass) {
        return components.containsKey(componentClass);
    }
}
