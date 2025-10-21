package myOwnVersion.GameState;

import java.util.ArrayList;
import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.PlayerCommunication.Player;

public class BasicGameState extends GameState {

    private final Principality redPrincipality;
    private final Principality bluePrincipality;
    private Principality currentPlayerPrincipality;

    private final DrawDeck roadDeck;
    private final DrawDeck settlementDeck;
    private final DrawDeck cityDeck;
    private final DrawDeck regionDeck;
    private final EventDrawDeck eventDeck;
    private final DrawDeck drawStack1;
    private final DrawDeck drawStack2;
    private final DrawDeck drawStack3;
    private final DrawDeck drawStack4;

    private DiscardPile discardPile;

    //Constructor
    public BasicGameState(Principality redPrincipality, Principality bluePrincipality,
            Principality currentPlayerPrincipality, DrawDeck roadDeck, DrawDeck settlementDeck,
            DrawDeck cityDeck, DrawDeck regionDeck, EventDrawDeck eventDeck, DrawDeck drawStack1,
            DrawDeck drawStack2, DrawDeck drawStack3, DrawDeck drawStack4, DiscardPile discardPile) {
        this.redPrincipality = redPrincipality;
        this.bluePrincipality = bluePrincipality;
        this.currentPlayerPrincipality = currentPlayerPrincipality;
        this.roadDeck = roadDeck;
        this.settlementDeck = settlementDeck;
        this.cityDeck = cityDeck;
        this.regionDeck = regionDeck;
        this.eventDeck = eventDeck;
        this.drawStack1 = drawStack1;
        this.drawStack2 = drawStack2;
        this.drawStack3 = drawStack3;
        this.drawStack4 = drawStack4;
        this.discardPile = discardPile;
    }

    public Principality getCurentPlayerPrincipality() {
        return currentPlayerPrincipality;
    }

    public Principality getNotCurrentPlayerPrincipality() {
        if (currentPlayerPrincipality == redPrincipality) {
            return bluePrincipality;
        } else {
            return redPrincipality;
        }
    }

    public void switchCurrentPlayer() {
        if (currentPlayerPrincipality == redPrincipality) {
            currentPlayerPrincipality = bluePrincipality;
        } else {
            currentPlayerPrincipality = redPrincipality;
        }
    }


    public DrawDeck getRoadDeck() {
        return roadDeck;
    }

    public DrawDeck getSettlementDeck() {
        return settlementDeck;
    }

    public DrawDeck getCityDeck() {
        return cityDeck;
    }

    public DrawDeck getRegionDeck() {
        return regionDeck;
    }

    public EventDrawDeck getEventDeck() {
        return eventDeck;
    }

    public DrawDeck getDrawStack1() {
        return drawStack1;
    }

    public DrawDeck getDrawStack2() {
        return drawStack2;
    }

    public DrawDeck getDrawStack3() {
        return drawStack3;
    }

    public DrawDeck getDrawStack4() {
        return drawStack4;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Print opposing player's principality
        Principality opposingPlayer = getNotCurrentPlayerPrincipality();
        sb.append("=== OPPOSING PLAYER ===\n");
        sb.append(opposingPlayer.toString()).append("\n\n");
        
        // Print draw decks in a row
        sb.append("=== DRAW DECKS ===\n");
        sb.append(String.format("Roads: %d | Settlements: %d | Cities: %d | Regions: %d | Events: %d\n",
            roadDeck.size(), settlementDeck.size(), cityDeck.size(), regionDeck.size(), eventDeck.size()));
        sb.append(String.format("Stack1: %d | Stack2: %d | Stack3: %d | Stack4: %d | Discard: %d\n\n",
            drawStack1.size(), drawStack2.size(), drawStack3.size(), drawStack4.size(), discardPile.size()));
        
        // Print current player's principality
        sb.append("=== CURRENT PLAYER ===\n");
        sb.append(currentPlayerPrincipality.toString()).append("\n");
        
        return sb.toString();
    }

    @Override
    public List<Principality> getAllPrincipalities() {
        List<Principality> principalities = new ArrayList<>();
        principalities.add(redPrincipality);
        principalities.add(bluePrincipality);
        return principalities;
    }



    @Override
    public Card drawCardFromType(String drawDeckType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawCardFromType'");
    }

    @Override
    public DrawDeck getDrawDeckFromName(String deckName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDrawDeckFromName'");
    }

    @Override
    public List<DrawDeck> getDrawDecksFromName(String deckName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDrawDecksFromName'");
    }

    @Override
    public Player getCurrentPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentPlayer'");
    }

    @Override
    public Player getPlayerWithStrengthAdvantage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerWithStrengthAdvantage'");
    }

    @Override
    public Player getOpposingPlayer(Player activePlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOpposingPlayer'");
    }


}
