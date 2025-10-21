package myOwnVersion.PlaymentRestricion;

import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.Principality.Principality;

public interface PlaymentRestriction {
    public boolean isPlaymentValid(Card card, Principality principality);
}
