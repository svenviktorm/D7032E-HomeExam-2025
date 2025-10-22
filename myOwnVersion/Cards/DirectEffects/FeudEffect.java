package myOwnVersion.Cards.DirectEffects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardComponents.CityCardComponent;
import myOwnVersion.Cards.CardComponents.PlacableCardComponent;
import myOwnVersion.Cards.CardComponents.RoadCardComponent;
import myOwnVersion.Cards.CardComponents.SettlementCardComponent;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;
import myOwnVersion.Cards.CardSystems.HasCardSymbolCardSystem;
import myOwnVersion.Cards.CardSymbol;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.GameState.DrawDeck;
import myOwnVersion.GameState.BasicGameState;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Position;
import myOwnVersion.GameState.Principality.Principality;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class FeudEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        Player[] players = playerCommunication.getPlayers();
        GameMaster gameMaster = GameMaster.getInstance();
        Principality advantagedPrincipality = gameState.getPrincipalityWithStrengthAdvantage();
        Player advantaged = gameMaster.getPlayerFromPrincipality(advantagedPrincipality);
        Player opponent = gameMaster.getOpposingPlayer(advantaged);

        // Collect opponent buildings
        List<Card> oppCards = opponent.getPrincipality().getPlayedCards();
        List<Card> oppBuildings = new ArrayList<>();
        for (Card c : oppCards) {
            if (isBuilding(c)) {
                oppBuildings.add(c);
            }
        }
        if (oppBuildings.isEmpty()) {
            return; // nothing to target
        }

        // Select up to 3 buildings; if 3 or fewer, auto-select all
        List<Card> marked = new ArrayList<>();
        if (oppBuildings.size() <= 3) {
            marked.addAll(oppBuildings);
        } else {
            // Ask advantaged player to mark 3 distinct buildings
            Set<Card> chosen = new HashSet<>();
            String prompt = "Feud: Choose an opponent building to mark for destruction (";
            for (int i = 0; i < 3; i++) {
                Card choice = playerCommunication.selectCardQuestion(
                        prompt + (i + 1) + "/3)", advantaged, oppBuildings, false);
                if (choice != null && !chosen.contains(choice)) {
                    chosen.add(choice);
                    marked.add(choice);
                    oppBuildings.remove(choice);
                } else {
                    // If invalid or duplicate, pick the first not yet chosen as fallback
                    for (Card candidate : oppBuildings) {
                        if (!chosen.contains(candidate)) {
                            chosen.add(candidate);
                            marked.add(candidate);
                            break;
                        }
                    }
                }
            }
        }

        // Opponent chooses which of the marked buildings to remove
        Card toRemove = null;
        if (marked.size() == 1) {
            toRemove = marked.get(0);
        } else {
            String removePrompt = "Feud: Choose one of the marked buildings to remove";
            toRemove = playerCommunication.selectCardQuestion(removePrompt, opponent, marked, false);
            if (toRemove == null) {
                toRemove = marked.get(0); // fallback
            }
        }

        // Remove from principality
        Position pos = toRemove.getComponent(PlacableCardComponent.class).position;
        opponent.getPrincipality().removeCard(pos);

        // Return to bottom of matching draw stack
        String deckName = inferDeckNameForCard(toRemove);
        DrawDeck deck = gameState.getDrawDecksFromName(deckName).get(0);
        deck.addCardBottom(toRemove);
    }

    private boolean isBuilding(Card c) {
        // Consider buildings as placeable cards that are not the three center types (Road/Settlement/City)
        if (!c.hasComponent(PlacableCardComponent.class)) return false;
        if (c.hasComponent(RoadCardComponent.class)) return false;
        if (c.hasComponent(SettlementCardComponent.class)) return false;
        if (c.hasComponent(CityCardComponent.class)) return false;
        return true;
    }

    private String inferDeckNameForCard(Card c) {
        return c.getComponent(StandardCardInfoComponent.class).matchingDrawPile;
    }
}
