package myOwnVersion.Cards.PermamentEffect;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.GameTurn.PlentifulHarvest;
import myOwnVersion.GameTurn.PlentifulHarvestTollBridge;
import myOwnVersion.PlayerCommunication.Player;

public class TollBridgeEffect implements PermanentEffect {
    private final Card origin;
    private PlentifulHarvest previousEffect;

    public TollBridgeEffect(Card origin) {
        this.origin = origin;
    }

    @Override
    public Card getOrigin() {
        return origin;
    }

    @Override
    public void restoreOriginalState(GameMaster gameMaster, GameState gameState) {
        gameMaster.overrideMethod(PlentifulHarvest.class, previousEffect);
    }

    @Override
    public void applyPermanentEffect(GameMaster gameMaster, GameState gameState) {
        Player currentPlayer = gameMaster.getGameTurn().getCurrentPlayer();
        PlentifulHarvest previousEffect = gameMaster.overrideMethod(PlentifulHarvest.class, new PlentifulHarvestTollBridge(currentPlayer));
        this.previousEffect = previousEffect;
    }

}
