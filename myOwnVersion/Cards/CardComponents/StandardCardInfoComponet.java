package myOwnVersion.Cards.CardComponents;

public class StandardCardInfoComponet implements CardComponent {
    public final String name, description, theme;

    public StandardCardInfoComponet(String name, String description, String theme) {
        this.name = name;
        this.description = description;
        this.theme = theme;
    }
}
