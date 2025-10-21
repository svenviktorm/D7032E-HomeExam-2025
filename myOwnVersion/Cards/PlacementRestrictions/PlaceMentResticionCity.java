package myOwnVersion.Cards.PlacementRestrictions;

import myOwnVersion.Cards.CardComponents.SettlementCardComponent;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

public class PlaceMentResticionCity implements PlacementRestriction {

    @Override
    public boolean isPlacementValid(Principality principality, Position position) {
        // check if the position contains a settlement
        if (principality.getCardAt(position) == null) {
            return false; // position must contain a settlement
        }

        if (!principality.getCardAt(position).hasComponent(SettlementCardComponent.class)) {
            return false; // position must contain a settlement
        }

        return true;
    }

}
