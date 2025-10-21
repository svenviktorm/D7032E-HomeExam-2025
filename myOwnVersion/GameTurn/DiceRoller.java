package myOwnVersion.GameTurn;

import myOwnVersion.GameState.GameState;

/**
 * Strategy interface for the production dice rolling phase.
 */
public interface DiceRoller extends Runnable{
    
    /**
     * Roll or otherwise determine the production die values for this turn.
     * @param gameState
     * @return an array of two integers representing the production die and event die values
     */
    int[] roll(GameState gameState);
}
