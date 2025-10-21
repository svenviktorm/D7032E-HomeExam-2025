package myOwnVersion.Cards.PlacementRestrictions;

import myOwnVersion.Cards.CardComponents.CityCardComponent;
import myOwnVersion.Cards.CardComponents.SettlementCardComponent;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

public class PlaceMentRestrictionRoad implements PlacementRestriction {
    @Override
    public boolean isPlacementValid(Principality principality, Position position) {
        // Roads can only be placed in column 0
        if (position.getColumn() != 0) {
            return false;

        }
        // Check if the position is empty
        if (principality.getCardAt(position) != null) {
            return false;
        }
        //check if there is a settlement or city before the road
        Position settlementPosition = null;
        if (position.getRow() > 0) {
            settlementPosition = new Position(1, position.getRow() - 1);
        } else if (position.getRow() < 0) {
            settlementPosition = new Position(-1, position.getRow() + 1);
        } else {
            return false; // Roads cannot be placed in the middle row
        }
        // Check if there is a settlement or city at the calculated position
        if (!(principality.getCardAt(settlementPosition).hasComponent(CityCardComponent.class) ||
            principality.getCardAt(settlementPosition).hasComponent(SettlementCardComponent.class))) {
            return false; // There must be a settlement or city before the road
        }
        return true;
    }

}
