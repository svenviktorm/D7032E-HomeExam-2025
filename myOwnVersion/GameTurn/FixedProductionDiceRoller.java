package myOwnVersion.GameTurn;

import myOwnVersion.GameState.GameState;

/**
 * A DiceRoller that returns a fixed value once, then delegates back to the previous roller.
 */
public class FixedProductionDiceRoller implements DiceRoller {
    private final int fixedValue;

    public FixedProductionDiceRoller(int fixedValue) {
        if (fixedValue < 1 || fixedValue > 6) {
            throw new IllegalArgumentException("Die value must be 1..6");
        }
        this.fixedValue = fixedValue;
    }

    @Override
    public int[] roll(GameState gameState) {
        return new int[]{fixedValue, new DefaultDiceRoller().roll(gameState)[1]};
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
