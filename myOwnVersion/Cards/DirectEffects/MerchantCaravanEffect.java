package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class MerchantCaravanEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        // Destroy 2 resources
        Player currentPlayer = gameTurn.getCurrentPlayer();
        String prompt = "Merchant Caravan Effect: Choose 2 resources to destroy.";
        playerCommunication.removeResourceAnyQuestion(prompt, currentPlayer, 2);

        // Gain 2 Resources
        String gainPrompt = "Merchant Caravan Effect: Choose 2 resources to gain.";
        playerCommunication.recieveResourceQuestion(gainPrompt, currentPlayer, false);
    }
}
