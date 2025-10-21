package myOwnVersion.GameTurn;

import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;

/**
 * Strategy interface for settlement placement during a game turn.
 */
public interface PostSettlementPlacement {
    void postPlaceSettlement(GameState gameState, Principality principality, Position position);
}
