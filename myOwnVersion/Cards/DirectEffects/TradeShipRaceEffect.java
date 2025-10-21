package myOwnVersion.Cards.DirectEffects;

import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class TradeShipRaceEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        Player[] players = playerCommunication.getPlayers();
        boolean hasShip = false;
        for (Player player : players) {
            Principality principality = player.getPrincipality();
            List<Card> cards = principality.getPlayedCards();
            for (Card card : cards) {
                String cardName = card.getComponent(StandardCardInfoComponent.class).name;
                if (cardName.equals("Trade Ship")) {
                    hasShip = true;
                    break;
                }
            }
        }
        if (!hasShip) {
            return; // No player has a ship
        }
        int firstPlayerShipCount = 0;
        Principality firstPrincipality = players[0].getPrincipality();
        for (Card cardd : firstPrincipality.getPlayedCards()) {
            String cardName = cardd.getComponent(StandardCardInfoComponent.class).name;
            if (cardName.equals("Trade Ship")) {
                firstPlayerShipCount++;
            }
        }
        int secondPlayerShipCount = 0;
        Principality secondPrincipality = players[1].getPrincipality();
        for (Card cardd : secondPrincipality.getPlayedCards()) {
            String cardName = cardd.getComponent(StandardCardInfoComponent.class).name;
            if (cardName.equals("Trade Ship")) {
                secondPlayerShipCount++;
            }
        }
        if (firstPlayerShipCount == secondPlayerShipCount) {
            String questionPrompt = "Trade Ship Race Event: You tied! Choose a resource to receive:";
            for (Player player : players) {
                playerCommunication.recieveResourceQuestion(questionPrompt, player, false);
            }
        } else {
            Player winner = firstPlayerShipCount > secondPlayerShipCount ? players[0] : players[1];
            String questionPrompt = "Trade Ship Race Event: You have the most trade ships! Choose a resource to receive:";
            playerCommunication.recieveResourceQuestion(questionPrompt, winner, false);
        }
    }
}
