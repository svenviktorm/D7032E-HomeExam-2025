package myOwnVersion.Cards.PermamentEffect;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.GameState;

public interface PermanentEffect {

    public Card getOrigin();

    public void restoreOriginalState(GameMaster gameMatster, GameState gameState);

    public void applyPermanentEffect(GameMaster gameMaster, GameState gameState);

}
