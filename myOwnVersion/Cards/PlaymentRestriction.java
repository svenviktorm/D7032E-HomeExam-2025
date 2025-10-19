package myOwnVersion.Cards;

import myOwnVersion.Principality;

public interface PlaymentRestriction {
    public boolean isPlaymentValid(Card card, Principality principality);
}
