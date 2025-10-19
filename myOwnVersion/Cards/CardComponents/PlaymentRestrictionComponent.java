package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.PlaymentRestriction;

public class PlaymentRestrictionComponent implements CardComponent {
    public final PlaymentRestriction restriction;

    public PlaymentRestrictionComponent(PlaymentRestriction restriction) {
        this.restriction = restriction;
    }
}
