package myOwnVersion.Cards.PlacementRestrictions;

import myOwnVersion.Cards.CardComponents.RoadCardComponent;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

public class PlaceMentRestricionSettlement implements PlacementRestriction {

    @Override
    public boolean isPlacementValid(Principality principality, Position position) {
        // Check if the position is empty
        if (principality.getCardAt(position) != null) {
            return false; // position must be empty
        }
        
        if (position.getColumn() != 0) {
            return false; // Settlements can only be placed in column 0
        }

        // Settlements can only be placed after a road
        Position roadPosition = null;
        if (position.getRow() > 0) {
            roadPosition = new Position(0, position.getRow() - 1);
        } else if (position.getRow() < 0) {
            roadPosition = new Position(0, position.getRow() + 1);
        } else {
            return false; // Settlements cannot be placed in the middle row
        }
        // Check if there is a road at the calculated position
        if (principality.getCardAt(roadPosition) == null ||
            !principality.getCardAt(roadPosition).hasComponent(RoadCardComponent.class)) {
            return false; // There must be a road before the settlement
        }
        return true;
    }

}
