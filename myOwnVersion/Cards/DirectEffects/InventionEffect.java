package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardSystems.HasCardSymbolCardSystem;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class InventionEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        Player[] players = playerCommunication.getPlayers();
        String questionPrompt = "Invention Event: Choose a resource to receive (optional):";
        for (Player player : players) {
            Principality principality = player.getPrincipality();
            int progressPoint = CardHandeler.getInstance()
                    .getCardSystem(HasCardSymbolCardSystem.class)
                    .getAllRewardsFromPrincipality(principality, CardSymbolType.PROGRESS_POINT)
                    .getAmount();
            for (int i = 0; i < Math.min(2, progressPoint); i++) {
                playerCommunication.recieveResourceQuestion(questionPrompt, player, true);
            }
        }
    }
}
