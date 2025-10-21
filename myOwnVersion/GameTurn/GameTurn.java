package myOwnVersion.GameTurn;

import java.util.ArrayList;

public class GameTurn {
    private ArrayList<Runnable> methods = new ArrayList<>();

    public myOwnVersion.PlayerCommunication.Player getCurrentPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentPlayer'");
    }

    /**
     * Inject a one-shot override of a specific method.
     * The next call to that method will use the provided replacement.
     */
    public void overrideMethod(Class type, Object replacement) {
        for (int i = 0; i < methods.size(); i++) {
            if (methods.get(i).getClass() == type) {
                methods.set(i, (Runnable) replacement);
                return;
            }
        }
    }

    /**
     * Called by the production phase. If an override is active, it will be consumed here.
     */
    public int[] rollProductionDie(myOwnVersion.GameState.GameState gameState) {
        DiceRoller diceRoller = getMethod(DiceRoller.class);
        return diceRoller.roll(gameState);
    }

    private DiceRoller getMethod(Class<DiceRoller> classType) {
        for (Runnable method : methods) {
            if (method.getClass() == classType) {
                return (DiceRoller) method;
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'getMethod'");
    }

}
