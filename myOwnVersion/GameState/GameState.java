package myOwnVersion.GameState;

import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.PlayerCommunication.Player;

public abstract class GameState {
    
    public abstract String toString();

    public abstract List<Principality> getAllPrincipalities();

    public abstract Card drawCardFromType(String drawDeckType);

    public abstract List<DrawDeck> getDrawDecksFromName(String deckName);

    public abstract Player getCurrentPlayer();

    public abstract Player getPlayerWithStrengthAdvantage();

    public abstract Player getOpposingPlayer(Player activePlayer);

    protected abstract GameState getInstance();
}
