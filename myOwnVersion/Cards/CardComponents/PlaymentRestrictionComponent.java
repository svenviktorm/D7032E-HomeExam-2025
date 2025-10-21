package myOwnVersion.Cards.CardComponents;

import myOwnVersion.PlaymentRestricion.PlaymentRestriction;

public class PlaymentRestrictionComponent implements CardComponent {
    public final PlaymentRestriction restriction;

    public PlaymentRestrictionComponent(PlaymentRestriction restriction) {
        this.restriction = restriction;
    }
}
