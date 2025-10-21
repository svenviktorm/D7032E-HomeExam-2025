package myOwnVersion.Cards.CardComponents;


import myOwnVersion.Cards.ActionType;
import myOwnVersion.Cards.WhenPlayable;
import myOwnVersion.Cards.DirectEffects.DirectEffect;

public class ActionCardComponent implements CardComponent {
    public final ActionType actionType;
    public final WhenPlayable whenPlayable;
    public final DirectEffect actionEffect;

    public ActionCardComponent(ActionType actionType, WhenPlayable whenPlayable, DirectEffect actionEffect) {
        this.actionType = actionType;
        this.whenPlayable = whenPlayable;
        this.actionEffect = actionEffect;
    }

}
