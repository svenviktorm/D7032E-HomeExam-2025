package myOwnVersion.Cards.PermamentEffect;

import myOwnVersion.GameMaster;
import myOwnVersion.PlayerAction;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardSystems.SymbolHoldingCardSystem;
import myOwnVersion.Cards.DirectEffects.DirectEffect;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class TwoToOneTradeEffect implements PermanentEffect {
    private final Card origin;
    private final CardSymbolType type;
    private PlayerAction action;
    private Player affectingPlayer;

    public TwoToOneTradeEffect(CardSymbolType type, Card origin) {
        this.origin = origin;
        this.type = type;
    }

    @Override
    public Card getOrigin() {
        return origin;
    }

    @Override
    public void restoreOriginalState(GameMaster gameMaster, GameState gameState) {
        gameMaster.removeActionFromPlayer(action, affectingPlayer);
    }

    @Override
    public void applyPermanentEffect(GameMaster gameMaster, GameState gameState) {
        
        String trigger = "2T1Trade"+type;
        String description = "Trade 2 " + type + " for 1 any resource.";
        
        DirectEffect effect = (gs, gt) -> {
            PlayerCommunication pc = PlayerCommunication.getInstance();
            Player currentPlayer = gt.getCurrentPlayer();
            Principality principality = currentPlayer.getPrincipality();
            SymbolHoldingCardSystem symbolSystem = CardHandeler.getInstance().getCardSystem(SymbolHoldingCardSystem.class);
            int availableResourceCount = symbolSystem.getAvailableResourcesOfType(type, principality);
            
            if (availableResourceCount < 2) {
                pc.sendMessageToPlayer("You do not have enough " + type + " to perform this trade.", currentPlayer);
                return;
            }

            String prompt = "Choose a resource to receive in exchange for 2 " + type + ":";
            pc.recieveResourceQuestion(prompt, currentPlayer, false);
            pc.removeResourceQuestion(prompt, currentPlayer, type, 2);

        };

        PlayerAction action = new PlayerAction(trigger, description, effect);

        gameMaster.addActionToPlayer(action, gameState.getCurrentPlayer());
        this.action = action;
        this.affectingPlayer = gameState.getCurrentPlayer();
    }

}
