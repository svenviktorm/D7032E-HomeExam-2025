package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.PlacementRestrictions.PlacementRestriction;
import myOwnVersion.GameState.Principality.Position;

public class PlacableCardComponent implements CardComponent {
    public final PlacementRestriction placementRestriction;
    public Position position;

    public PlacableCardComponent(PlacementRestriction placementRestriction) {
        this.placementRestriction = placementRestriction;
    }
}
