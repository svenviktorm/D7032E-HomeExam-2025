package myOwnVersion.Cards.PlacementRestrictions;

import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

public interface PlacementRestriction {
    public boolean isPlacementValid(Principality principality, Position position);
}
