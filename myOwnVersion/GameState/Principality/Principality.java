package myOwnVersion.GameState.Principality;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardSymbol;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardSystems.CardSystem;
import myOwnVersion.Cards.CardSystems.HasCardSymbolCardSystem;
import myOwnVersion.Cards.CardSystems.PrintCardSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Principality {

    private Map<String, Card> grid;
    private int minRow;
    private int maxRow;
    private int minCol;
    private int maxCol;

    /**
     * Creates a new Principality with no starting cards.
     * Grid boundaries are initialized at origin (0,0).
     */
    public Principality() {
        this.grid = new HashMap<>();
        // Initialize grid boundaries at origin
        this.minRow = 0;
        this.maxRow = 0;
        this.minCol = 0;
        this.maxCol = 0;
    }

    /**
     * Creates a new Principality with a starting setup.
     * @param startingSetup Map of positions to cards for the initial setup
     */
    public Principality(Map<Position, Card> startingSetup) {
        this.grid = new HashMap<>();
        // Initialize grid boundaries at origin
        this.minRow = 0;
        this.maxRow = 0;
        this.minCol = 0;
        this.maxCol = 0;
        
        // Place all starting cards
        if (startingSetup != null) {
            for (Map.Entry<Position, Card> entry : startingSetup.entrySet()) {
                Position position = entry.getKey();
                placeCard(position, entry.getValue());
                grid.put(translatePosition(position), entry.getValue());
            }
        }
    }

    

    /**
     * Places a card at the specified position in the grid.
     * Automatically expands the grid boundaries if needed.
     * @param position The position to place the card
     * @param card The card to place
     */
    public void placeCard(Position position, Card card) {
        grid.put(translatePosition(position), card);
        
        // Update boundaries
        if (position.getRow() < minRow) {
            minRow = position.getRow();
        }
        if (position.getRow() > maxRow) {
            maxRow = position.getRow();
        }
        if (position.getColumn() < minCol) {
            minCol = position.getColumn();
        }
        if (position.getColumn() > maxCol) {
            maxCol = position.getColumn();
        }
    }

    /**
     * Gets the card at the specified position.
     * @param position The position to check
     * @return The card at that position, or null if empty
     */
    public Card getCardAt(Position position) {
        return grid.get(translatePosition(position));
    }

    private String translatePosition(Position position) {
        return "pos"+position.getColumn()+"@"+position.getRow();
    }

    private Position translatePositionReverse(String key) {
        key = key.replaceFirst("pos", "");
        String[] parts = key.split("@");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid position key: " + key);
        }
        int col = Integer.parseInt(parts[0]);
        int row = Integer.parseInt(parts[1]);
        return new Position(col, row);
    }

    /**
     * Removes a card from the specified position.
     * @param position The position to remove the card from
     * @return The removed card, or null if no card was at that position
     */
    public Card removeCard(Position position) {
        return grid.remove(translatePosition(position));
    }

    /**
     * Checks if a position has a card placed on it.
     * @param position The position to check
     * @return true if a card exists at that position, false otherwise
     */
    public boolean hasCardAt(Position position) {
        return grid.containsKey(position);
    }

    /**
     * Gets all cards currently played in the principality.
     * @return Array of all cards in the grid
     */
    public List<Card> getPlayedCards() {
        return new ArrayList<>(grid.values());
    }

    /**
     * Gets all positions with cards in the principality.
     * @return List of all positions that have cards
     */
    public List<Position> getOccupiedPositions() {
        List<Position> positions = new ArrayList<>();
        for (String key : grid.keySet()) {
            positions.add(translatePositionReverse(key));
        }
        return positions;
    }

    /**
     * Gets the total number of cards in the principality.
     * @return The number of cards
     */
    public int getCardCount() {
        return grid.size();
    }

    /**
     * Clears all cards from the principality and resets boundaries.
     */
    public void clear() {
        grid.clear();
        minRow = 0;
        maxRow = 0;
        minCol = 0;
        maxCol = 0;
    }

    @Override
    public String toString() {
        PrintCardSystem cardPrint = CardHandeler.getInstance().getCardSystem(PrintCardSystem.class);
        StringBuilder sb = new StringBuilder();
        // Print grid using CardSystem
        int maxCardStringSize = 0;
        for (Card card : getPlayedCards()) {
            String cardStr = cardPrint.printCard(card);
            if (cardStr.length() > maxCardStringSize) {
                maxCardStringSize = cardStr.length();
            }
        }


        for (int col = minCol; col <= maxCol; col++) {
            for (int row = minRow; row <= maxRow; row++) {
                Position pos = new Position(col, row);
                Card card = getCardAt(pos);
                if (card != null) {
                    // Use CardSystem to print card (to be implemented)
                    String cardStr = cardPrint.printCard(card);
                    int stringSize = cardStr.length();
                    int padding = (maxCardStringSize - stringSize)/2;
                    int offset = (maxCardStringSize - stringSize)%2;
                    String paddingOffset = getPadding(offset);
                    String stringPadding = getPadding(padding);
                    sb.append("[" + stringPadding + cardStr + stringPadding + paddingOffset + "]");
                } else {
                    int stringSize = "   ".length();
                    int padding = (maxCardStringSize - stringSize)/2;
                    String stringPadding = getPadding(padding);
                    int offset = (maxCardStringSize - stringSize)%2;
                    String paddingOffset = getPadding(offset);
                    sb.append("[" + stringPadding + "   " + stringPadding + paddingOffset + "]");
                }
                if (row < maxRow) {
                    sb.append(" ");
                }


            }
            sb.append("\n");
        }
        
        // Get and display all non-resource rewards from the principality
        sb.append("\n=== Card Symbol Rewards (Non-Resources) ===\n");
        HasCardSymbolCardSystem cardSystem = new HasCardSymbolCardSystem();
        
        // Define non-resource symbol types
        CardSymbolType[] nonResourceTypes = {
            CardSymbolType.VICTORY_POINT,
            CardSymbolType.SKILL_POINT,
            CardSymbolType.PROGRESS_POINT,
            CardSymbolType.COMMERCE_POINT,
            CardSymbolType.STRENGTH_POINT
        };
        
        for (CardSymbolType type : nonResourceTypes) {
            CardSymbol reward = cardSystem.getAllRewardsFromPrincipality(this, type);
            if (reward.getAmount() > 0) {
                sb.append(type.name()).append(": ").append(reward.getAmount()).append(" ");
            }
        }
        
        return sb.toString();
    }

    private String getPadding(int offset) {
        String padding = "";
        for (int i = 0; i < offset; i++) {
            padding += " ";
        }
        return padding;
    }
    
}
