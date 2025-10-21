package myOwnVersion.Cards.CardComponents;

public class StandardCardInfoComponent implements CardComponent {
    public final String name, description;
    public final String matchingDrawPile;

    public StandardCardInfoComponent(String name, String description, String matchingDrawPile) {
        this.name = name;
        this.description = description;
        this.matchingDrawPile = matchingDrawPile;
    }
}
