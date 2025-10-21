package myOwnVersion.PlayerCommunication;

import java.util.ArrayList;
import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.GameState.DrawDeck;

public class PlayerCommunication {

    private static PlayerCommunication instance = null;

    public static PlayerCommunication getInstance() {
        if (instance == null) {
            instance = new PlayerCommunication();
        }
        return instance;
    }

    public Player[] getPlayers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayers'");
    }

    /**
     * Ask the player to recieve a resource of their choice.
     * @param questionPrompt The prompt to show the player.
     * @param player The player to ask.
     * @param optional If true, the player can choose to not recieve a resource.
     * @return 
     */
    public boolean recieveResourceQuestion(String questionPrompt, Player player, boolean optional) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recieveResourceQuestion'");
    }

    public void removeResourceQuestion(String questionPrompt, Player player, CardSymbolType gold, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeResourceQuestion'");
    }

    public Card selectCardQuestion(String removePrompt, Player opponent, List<Card> opposingCards, boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectCardQuestion'");
    }

    public String askPlayerQuestion(String prompt, Player currentPlayer, boolean b, String acceptedResponseRegex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askPlayerQuestion'");
    }

    public void removeResourceAnyQuestion(String prompt, Player currentPlayer, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeResourceAnyQuestion'");
    }

    public void sendMessageToPlayer(String string, Player currentPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessageToPlayer'");
    }

    public <T> T selectObjectQuestion(String prompt, Player activePlayer, List<T> drawDecks1, boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectObjectQuestion'");
    }


}
