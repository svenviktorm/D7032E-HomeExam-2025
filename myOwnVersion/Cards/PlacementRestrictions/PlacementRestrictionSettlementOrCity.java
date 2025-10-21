package myOwnVersion.Cards.PlacementRestrictions;

import myOwnVersion.Cards.CardComponents.CityCardComponent;
import myOwnVersion.Cards.CardComponents.SettlementCardComponent;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

public class PlacementRestrictionSettlementOrCity implements PlacementRestriction {
    @Override
    public boolean isPlacementValid(Principality principality, Position position) {
        Position middlePosition = new Position(position.getColumn(), 0);
        if (!(principality.getCardAt(middlePosition).hasComponent(CityCardComponent.class) ||
            principality.getCardAt(middlePosition).hasComponent(SettlementCardComponent.class))) {
            return false; // Cannot place if there's is no settlement or city
        }
        if (!(position.getColumn() == 1 || position.getColumn() == -1)) {
            return false; // Must be directly above or below a settlement or city
        }
        if (principality.getCardAt(position) != null) {
            return false; // position must be empty
        }
        return true;
    }

}
