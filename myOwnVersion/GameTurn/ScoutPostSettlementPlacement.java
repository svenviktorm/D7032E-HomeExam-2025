package myOwnVersion.GameTurn;

import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.DrawDeck;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.PlayerCommunication.PlayerCommunication;
/**
 * Strategy for settlement placement when the Scout card effect is used.
 * Places two region cards adjacent to the newly placed settlement.
 */
public class ScoutPostSettlementPlacement implements PostSettlementPlacement {
    @Override
    public void postPlaceSettlement(GameState gameState, Principality principality, Position position) {
        // Obtain all region cards from the region draw deck
        DrawDeck regionDeck = gameState.getDrawDecksFromName("Region").get(0);
        if (regionDeck == null) {
            throw new IllegalStateException("Region draw deck not found.");
        }
        List<Card> available = regionDeck.getCards();
        if (available == null || available.isEmpty()) {
            throw new IllegalStateException("No region cards available.");
        }

        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();

        Card firstChoice = playerCommunication.selectCardQuestion("Select two region cards to place adjacent to your settlement.", gameState.getCurrentPlayer(), available, false);
        available.remove(firstChoice);
        Card secondChoice = playerCommunication.selectCardQuestion("Select another region card to place adjacent to your settlement.", gameState.getCurrentPlayer(), available, false);


        // Remove chosen cards from the deck
        regionDeck.removeCard(firstChoice);
        if (secondChoice != null) {
            regionDeck.removeCard(secondChoice);
        }

        // Determine direction based on settlement column sign
        int dir = position.getColumn() >= 0 ? 1 : -1;
        Position posUpper = new Position(position.getColumn() + dir, position.getRow() + 1);
        Position posLower = new Position(position.getColumn() + dir, position.getRow() - 1);

        // Place chosen region cards, first on upper edge, second on lower edge
        String firstRegionName = firstChoice.getComponent(StandardCardInfoComponent.class).name;
        String secondRegionName = secondChoice.getComponent(StandardCardInfoComponent.class).name;

        String prompt = "You have drawn two region cards: " + firstRegionName + "[0] and " + secondRegionName + "[1]. Which one do you want to place on the upper position?";
        String answer = playerCommunication.askPlayerQuestion(prompt, gameState.getCurrentPlayer(), false, "^(0|1)$");
        if (answer.equals("0")) {
            principality.placeCard(posUpper, firstChoice);
            principality.placeCard(posLower, secondChoice);
        } else {
            principality.placeCard(posUpper, secondChoice);
            principality.placeCard(posLower, firstChoice);
        }
    }

}
