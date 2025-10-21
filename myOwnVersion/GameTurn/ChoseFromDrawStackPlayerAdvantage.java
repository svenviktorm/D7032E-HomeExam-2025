package myOwnVersion.GameTurn;

import myOwnVersion.GameMaster;
import myOwnVersion.PlayerCommunication.Player;

public class ChoseFromDrawStackPlayerAdvantage extends ChoseFromDrawStackDefalt {
    Player affectingPlayer;

    //constructor
    public ChoseFromDrawStackPlayerAdvantage(int i, Player currentPlayer) {
        super(i);
        this.affectingPlayer = currentPlayer;
    }
    @Override
    public void run() {
        Player currentPlayer = GameMaster.getInstance().getGameState().getCurrentPlayer();
        if (currentPlayer.equals(affectingPlayer)) {
            super.run();
        } else {
            // Call the default implementation for other players
            new ChoseFromDrawStackDefalt(1).run();
        }
    }
}
