package myOwnVersion.GameTurn;

import java.util.concurrent.ThreadLocalRandom;

import myOwnVersion.GameState.GameState;

public class DefaultDiceRoller implements DiceRoller {
    @Override
    public int[] roll(GameState gameState) {
        return new int[]{ThreadLocalRandom.current().nextInt(1, 7), ThreadLocalRandom.current().nextInt(1, 7)};
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
