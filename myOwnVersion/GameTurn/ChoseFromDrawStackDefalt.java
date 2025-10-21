package myOwnVersion.GameTurn;

import java.util.List;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.DrawDeck;
import myOwnVersion.GameState.GameState;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class ChoseFromDrawStackDefalt implements ChoseFromDrawStack{

    private final int resourceCost;

    public ChoseFromDrawStackDefalt(int resourceCost) {
        this.resourceCost = resourceCost;
    }

    @Override
    public void run() {
        // make the user pay the resource cost
        GameMaster gameMaster = GameMaster.getInstance();
        Player currentPlayer = gameMaster.getGameState().getCurrentPlayer();
        PlayerCommunication playerComm = PlayerCommunication.getInstance();
        String question = "Pay " + resourceCost + " resources to choose from draw stack";
        playerComm.removeResourceAnyQuestion(question, currentPlayer, resourceCost);

        GameState gameState = gameMaster.getGameState();
        List<DrawDeck> drawStackNames = gameState.getDrawDecksFromName("default");

        DrawDeck chosenDrawStack = playerComm.selectObjectQuestion(question, currentPlayer, drawStackNames, false);

        // let the user choose a card from the chosen draw stack
        List<Card> availableCards = chosenDrawStack.getCards();

        Card chosenCard = playerComm.selectObjectQuestion("Choose a card from the draw stack", currentPlayer, availableCards, false);

        currentPlayer.addCardToHand(chosenCard);
        chosenDrawStack.removeCard(chosenCard);
            
        
    }

}
