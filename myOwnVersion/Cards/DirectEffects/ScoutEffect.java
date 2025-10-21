package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.GameTurn.PostSettlementPlacement;
import myOwnVersion.GameTurn.ScoutPostSettlementPlacement;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class ScoutEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication pc = PlayerCommunication.getInstance();
        PostSettlementPlacement settlementPlacement = new ScoutPostSettlementPlacement();
        gameTurn.overrideMethod(PostSettlementPlacement.class, settlementPlacement);
    }
}
