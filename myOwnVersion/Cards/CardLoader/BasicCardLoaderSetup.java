package myOwnVersion.Cards.CardLoader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardBuilder.JsonCardBuilder;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.BasicGameState;
import myOwnVersion.GameState.GameStateSetup.GameStateSetup;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class BasicCardLoaderSetup extends CardLoaderSetup {
    
    public BasicCardLoaderSetup(PostCardLoader postCardLoader, GameStateSetup gameStateSetup) {
        super(postCardLoader, gameStateSetup);
    }


    private static final String CARDS_JSON_PATH = "myOwnVersion/Cards/cards.json";
    
    @Override
    public GameState setup(String theme) {
        // 1. Load all cards with matching theme from cards.json
        List<Card> loadedCards = loadCardsFromJson(theme);
        
        // 2. Pass cards through PostCardLoader (not implemented yet)
        List<Card> processedCards = postCardLoader.process(loadedCards);

        // 3. Use setupConditionCardComponent to determine NormalGameState setup
        GameState gameState = gameStateSetup.setup(processedCards);
        
        return gameState;
    }
    
    /**
     * Loads all cards from cards.json that have the specified theme.
     * Creates multiple card instances based on the "number" field.
     * @param theme The theme to filter cards by (e.g., "Basic")
     * @return List of built Card objects
     */
    private List<Card> loadCardsFromJson(String theme) {
        List<Card> cards = new ArrayList<>();
        JsonCardBuilder cardBuilder = new JsonCardBuilder();
        
        try (FileReader reader = new FileReader(CARDS_JSON_PATH)) {
            JsonElement root = JsonParser.parseReader(reader);
            
            if (!root.isJsonArray()) {
                throw new IOException("Expected JSON array in cards.json");
            }
            
            JsonArray cardArray = root.getAsJsonArray();
            
            for (JsonElement element : cardArray) {
                if (!element.isJsonObject()) {
                    continue;
                }
                
                JsonObject cardJson = element.getAsJsonObject();
                
                // Check if card has the matching theme
                if (cardJson.has("theme") && !cardJson.get("theme").isJsonNull()) {
                    String cardTheme = cardJson.get("theme").getAsString();
                    
                    if (cardTheme.contains(theme)) {
                        // Get the number of copies to create (default to 1)
                        int number = 1;
                        if (cardJson.has("number") && !cardJson.get("number").isJsonNull()) {
                            number = cardJson.get("number").getAsInt();
                        }
                        
                        // Create 'number' copies of this card
                        for (int i = 0; i < number; i++) {
                            Card card = cardBuilder.buildCard(cardJson);
                            cards.add(card);
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error loading cards from JSON: " + e.getMessage());
            e.printStackTrace();
        }
        
        return cards;
    }
    
    
    /**
     * Sets up the NormalGameState using setupConditionCardComponent from the cards.
     * @param cards The list of processed cards
     * @return A configured NormalGameState
     */
    private BasicGameState setupGameState(List<Card> cards) {
        // TODO: Implement game state setup using setupConditionCardComponent
        // This should:
        // 1. Separate cards into different decks (roads, settlements, cities, regions, events, draw stacks)
        // 2. Create two principalities with starting setups
        // 3. Initialize all the decks and discard pile
        // 4. Return a properly configured NormalGameState
        
        throw new UnsupportedOperationException("Game state setup not yet implemented");
    }

}
