package myOwnVersion.GameTurn;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class PostStandardSettlementPlacement implements PostSettlementPlacement {

    @Override
    public void postPlaceSettlement(GameState gameState, Principality principality, Position position) {
        // Draw two region cards from the game state
        Card firstRegion = gameState.drawCardFromType("Region");
        Card secondRegion = gameState.drawCardFromType("Region");

        // If the deck(s) are empty, do nothing for missing cards
        if (firstRegion == null && secondRegion == null) {
            throw new IllegalStateException("No region cards available to place.");
        }

        // Determine direction based on settlement column
        // Positive column -> place to the positive (right) side
        // Negative column -> place to the negative (left) side
        int dir = position.getColumn() >= 0 ? 1 : -1;

        Position posUpper = new Position(position.getColumn() + dir, position.getRow() + 1);
        Position posLower = new Position(position.getColumn() + dir, position.getRow() - 1);

        // Place the region cards

        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        String firstRegionName = firstRegion.getComponent(StandardCardInfoComponent.class).name;
        String secondRegionName = secondRegion.getComponent(StandardCardInfoComponent.class).name;

        String prompt = "You have drawn two region cards: " + firstRegionName + "[0] and " + secondRegionName + "[1]. Which one do you want to place on the upper position?";
        String answer = playerCommunication.askPlayerQuestion(prompt, gameState.getCurrentPlayer(), false, "^(0|1)$");
        if (answer.equals("0")) {
            principality.placeCard(posUpper, firstRegion);
            principality.placeCard(posLower, secondRegion);
        } else {
            principality.placeCard(posUpper, secondRegion);
            principality.placeCard(posLower, firstRegion);
        }
        
    }

}
