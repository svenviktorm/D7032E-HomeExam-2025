package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.DirectEffects.DirectEffect;

public class EventCardComponent implements CardComponent {
    public final DirectEffect eventEffect;

    public EventCardComponent(DirectEffect eventEffect) {
        this.eventEffect = eventEffect;
    }

}
