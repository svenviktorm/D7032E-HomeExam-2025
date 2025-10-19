package myOwnVersion.Cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import myOwnVersion.Cards.CardSystems.CardSystem;

public class CardHandeler {
    private static CardHandeler instance;

    private final List<Card> cards = new ArrayList<>();
    private final HashMap<Class<CardSystem>, CardSystem> systems = new HashMap<>();
    private int nextCardID = 0;

    /**
     * Gets the cardHandler from a static context, also ensures that there can only be one instance
     * @return
     */
    public static CardHandeler getInstance() {
            if (instance == null) {
                instance = new CardHandeler();
            }
            return instance;
    }

    /**
     * Creates a blank kard with uniqe ID
     * @return the created Card
     */
    public Card createCard() {
        Card newCard = new Card(nextCardID++);
        cards.add(newCard);
        return newCard;
    }

    /**
     * add a cardSystem to the CardHandeler will replace any cardSystem off the same class.
     * @param systemClass
     * @return the previous cardSystem off the same class or null.
     */
    public CardSystem addCardSystem(CardSystem cardSystem) {
        return systems.put((Class<CardSystem>) cardSystem.getClass(), cardSystem);
    }

    /**
     * Gets the cardsSystem of the specified Class
     * @param systemClass
     * @return the precent cardSystem or null
     */
    public <T extends CardSystem> T getCardSystem(Class<T> systemClass) {
        return (T) systems.get(systemClass);
    }

    
}
