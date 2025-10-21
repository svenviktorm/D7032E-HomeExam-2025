package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardSystems.HasCardSymbolCardSystem;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class GoldsmithEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        Player currentPlayer = gameTurn.getCurrentPlayer();
        
        Principality principality = currentPlayer.getPrincipality();

        int gold = CardHandeler.getInstance().getCardSystem(HasCardSymbolCardSystem.class)
                .getAllRewardsFromPrincipality(principality, CardSymbolType.GOLD)
                .getAmount();

        if (gold < 3) {
            return; // Not enough gold to exchange
        }
        // Exchange 3 gold for 2 resources
        String prompt = "Goldsmith Effect: Choose what gold to remove.";
        playerCommunication.removeResourceQuestion(prompt, currentPlayer, CardSymbolType.GOLD, 3);

        String receivePrompt = "Goldsmith Effect: Choose 2 resources to receive.";
        for (int i = 0; i < 2; i++) {
            playerCommunication.recieveResourceQuestion(receivePrompt, currentPlayer, false);
        }
    }
}
