package myOwnVersion.Cards.CardComponents;

public class StandardCardInfoComponet implements CardComponent {
    public final String name, description;

    public StandardCardInfoComponet(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
