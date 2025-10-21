package myOwnVersion.Cards.PermamentEffect;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.ChoseFromDrawStack;
import myOwnVersion.GameTurn.ChoseFromDrawStackPlayerAdvantage;
import myOwnVersion.PlayerCommunication.Player;

public class ParishHallEffect implements PermanentEffect {
    private final Card origin;
    private ChoseFromDrawStack oldEffect;

    public ParishHallEffect(Card origin) {
        this.origin = origin;
    }

    @Override
    public Card getOrigin() {
        return origin;
    }

    @Override
    public void restoreOriginalState(GameMaster gameMaster, GameState gameState) {
        gameMaster.overrideMethod(ChoseFromDrawStack.class, oldEffect);
    }

    @Override
    public void applyPermanentEffect(GameMaster gameMaster, GameState gameState) {
        Player currentPlayer = gameState.getCurrentPlayer();
        oldEffect = gameMaster.overrideMethod(ChoseFromDrawStack.class, new ChoseFromDrawStackPlayerAdvantage(2, currentPlayer));
    }

}
