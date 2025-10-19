package myOwnVersion.Cards.CardComponents;

public class ActionCardComponent implements CardComponent {
    public final WhenPlayable whenPlayable;
    public final ActionEffect actionEffect;

    public ActionCardComponent(WhenPlayable whenPlayable, ActionEffect actionEffect) {
        this.whenPlayable = whenPlayable;
        this.actionEffect = actionEffect;
    }

}
