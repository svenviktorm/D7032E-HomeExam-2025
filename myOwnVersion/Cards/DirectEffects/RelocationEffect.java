package myOwnVersion.Cards.DirectEffects;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardComponents.PlacableCardComponent;
import myOwnVersion.Cards.CardComponents.RegionCardComponent;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.Cards.CardSystems.CardRemoverSystem;
import myOwnVersion.Cards.PlacementRestrictions.PlacementRestrictionSettlementOrCity;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class RelocationEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        // Choose two cards to swap locations
        String prompt = "Relocation Effect: Choose two cards to swap locations.";
        Player currentPlayer = gameTurn.getCurrentPlayer();
        ArrayList<Card> options = new ArrayList<>();
        List<Card> playedCards = currentPlayer.getPrincipality().getPlayedCards();
        for (Card card : playedCards) {
            options.add(card);
        }
        Card firstChoice = playerCommunication.selectCardQuestion(prompt, currentPlayer, options, false);
        options.remove(firstChoice);
        Card secondChoice = playerCommunication.selectCardQuestion(prompt, currentPlayer, options, false);

        // Check if both choices are valid
        if (firstChoice == null && secondChoice == null) {
            throw new IllegalStateException("RelocationEffect: Both selected cards are null.");
        }
        PlacableCardComponent firstInfo = firstChoice.getComponent(PlacableCardComponent.class);
        PlacableCardComponent secondInfo = secondChoice.getComponent(PlacableCardComponent.class);

        if (!(firstInfo.placementRestriction.getClass() == secondInfo.placementRestriction.getClass())) {
            playerCommunication.sendMessageToPlayer("Relocation Effect: Selected cards have incompatible placement restrictions. Try again.", currentPlayer);
            this.applyEffect(gameState, gameTurn);
        }
        boolean regionCard = firstChoice.hasComponent(RegionCardComponent.class) && secondChoice.hasComponent(RegionCardComponent.class);
        boolean expansionCard = firstChoice.getComponent(PlacableCardComponent.class).placementRestriction.getClass() == PlacementRestrictionSettlementOrCity.class && 
                                secondChoice.getComponent(PlacableCardComponent.class).placementRestriction.getClass() == PlacementRestrictionSettlementOrCity.class;
        if (regionCard || expansionCard) {
            Principality principality = currentPlayer.getPrincipality();
            CardRemoverSystem cardRemoverSystem = CardHandeler.getInstance().getCardSystem(CardRemoverSystem.class);
            Position firstPosition = firstChoice.getComponent(PlacableCardComponent.class).position;
            Position secondPosition = secondChoice.getComponent(PlacableCardComponent.class).position;

            Card removedCard1 = cardRemoverSystem.removeCardFromPrincipality(principality, firstChoice);
            Card removedCard2 = cardRemoverSystem.removeCardFromPrincipality(principality, secondChoice);

            // Swap the positions of the two cards
            firstChoice.getComponent(PlacableCardComponent.class).position = secondPosition;
            secondChoice.getComponent(PlacableCardComponent.class).position = firstPosition;

            principality.placeCard(secondPosition, removedCard1);
            principality.placeCard(firstPosition, removedCard2);
        }
    }
}
