package myOwnVersion;

import java.util.List;

import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.GameTurn.PlentifulHarvest;
import myOwnVersion.GameTurn.PlentifulHarvestTollBridge;
import myOwnVersion.PlayerCommunication.Player;

public class GameMaster {

    private static GameMaster instance;

    public static GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    public void addActionToPlayer(PlayerAction action, Player currentPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addActionToPlayer'");
    }

    public void removeActionFromPlayer(PlayerAction action, Player affectingPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeActionFromPlayer'");
    }

    public GameState getGameState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
    }

    public List<Player> getAllPlayers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPlayers'");
    }

    public GameTurn getGameTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameTurn'");
    }

    public <T> T overrideMethod(Class<T> classType, T method) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'overrideMethod'");
    }



}
