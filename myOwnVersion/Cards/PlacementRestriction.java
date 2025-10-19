package myOwnVersion.Cards;

import myOwnVersion.Principality;

public interface PlacementRestriction {
    public boolean isPlacementValid(Card card, Principality principality, Position position);
}
