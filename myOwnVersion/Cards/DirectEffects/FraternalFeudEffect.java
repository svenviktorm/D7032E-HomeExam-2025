package myOwnVersion.Cards.DirectEffects;

import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.GameState.DrawDeck;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class FraternalFeudEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        Player activePlayer = gameState.getPlayerWithStrengthAdvantage();
        if (activePlayer == null) {
            return; // No player has strength advantage; nothing to do
        }
        Player opposingPlayer = gameState.getOpposingPlayer(activePlayer);

        List<Card> opposingCards = opposingPlayer.getCardsInHand();
        String prompt = "Fraternal Feud activated: Choose two cards to discard from your hand";
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        Card firstCardToDiscard = playerCommunication.selectCardQuestion(prompt, activePlayer, opposingCards, false);
        opposingCards.remove(firstCardToDiscard);
        Card secondCardToDiscard = playerCommunication.selectCardQuestion(prompt, activePlayer, opposingCards, false);

        opposingPlayer.discardCard(firstCardToDiscard);
        opposingPlayer.discardCard(secondCardToDiscard);

        StandardCardInfoComponent firstCardInfo = firstCardToDiscard.getComponent(StandardCardInfoComponent.class);
        StandardCardInfoComponent secondCardInfo = secondCardToDiscard.getComponent(StandardCardInfoComponent.class);

        String matchingDrawPile1 = firstCardInfo.matchingDrawPile;
        String matchingDrawPile2 = secondCardInfo.matchingDrawPile;

        List<DrawDeck> drawDecks1 = gameState.getDrawDecksFromName(matchingDrawPile1);
        List<DrawDeck> drawDecks2 = gameState.getDrawDecksFromName(matchingDrawPile2);

        prompt = "Fraternal Feud: Choose one of the drawpiles to return to the card to its draw pile.";

        DrawDeck chosenDeck1 = PlayerCommunication.getInstance().selectObjectQuestion(prompt, activePlayer, drawDecks1, false);
        chosenDeck1.addCardBottom(firstCardToDiscard);

        DrawDeck chosenDeck2 = PlayerCommunication.getInstance().selectObjectQuestion(prompt, activePlayer, drawDecks2, false);
        chosenDeck2.addCardBottom(secondCardToDiscard);

    }
}
