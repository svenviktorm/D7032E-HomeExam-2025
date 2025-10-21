package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardSystems.HasCardSymbolCardSystem;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class TravelingMerchantEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        HasCardSymbolCardSystem system = CardHandeler.getInstance().getCardSystem(HasCardSymbolCardSystem.class);
        Player[] players = playerCommunication.getPlayers();
        String questionPrompt = "Traveling Merchant Event: Choose a resource to receive (pay 1 gold each, optional):";
        for (Player player : players) {
            boolean continueChoosing = true;
            while (continueChoosing && system.getAllRewardsFromPrincipality(player.getPrincipality(), CardSymbolType.GOLD).getAmount() > 0) {
                continueChoosing = playerCommunication.recieveResourceQuestion(questionPrompt, player, true);
                if (continueChoosing) {
                    playerCommunication.removeResourceQuestion(questionPrompt,  player, CardSymbolType.GOLD, 1);
                }
            }
        }
    }
}
