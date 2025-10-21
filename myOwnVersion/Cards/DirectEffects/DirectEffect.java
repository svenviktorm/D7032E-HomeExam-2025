package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.GameTurn;

public interface DirectEffect {
    void applyEffect(GameState gameState, GameTurn gameTurn);
}
