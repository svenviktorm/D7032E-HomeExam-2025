package myOwnVersion.Cards.CardBuilder;

import com.google.gson.JsonObject;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardSymbol;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.DirectEffects.DirectEffect;
import myOwnVersion.Cards.DirectEffects.DirectEffectFactory;
import myOwnVersion.Cards.PermamentEffect.PermanentEffect;
import myOwnVersion.Cards.PermamentEffect.PermanentEffectFactory;
import myOwnVersion.Cards.WhenPlayable;
import myOwnVersion.Cards.ActionType;
import myOwnVersion.Cards.CardComponents.*;
import myOwnVersion.Cards.PlacementRestrictions.PlaceMentResticionCity;
import myOwnVersion.Cards.PlacementRestrictions.PlaceMentRestricionSettlement;
import myOwnVersion.Cards.PlacementRestrictions.PlaceMentRestrictionRoad;
import myOwnVersion.Cards.PlacementRestrictions.PlacementRestriction;
import myOwnVersion.Cards.PlacementRestrictions.PlacementRestrictionSettlementOrCity;
import myOwnVersion.PlaymentRestricion.PlaymentRestriction;
import myOwnVersion.PlaymentRestricion.PlaymentRestrictionOneOf;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of CardBuilder that creates Cards from JSON objects
 */
public class JsonCardBuilder implements CardBuilder {
    @Override
    public Card buildCard(JsonObject jsonObject) {
        Card card = CardHandeler.getInstance().createCard();

        // Add components based on the JSON data
        addStandardCardInfo(card, jsonObject);
        addResourceComponents(card, jsonObject);
        addCenterCardComponent(card, jsonObject);
        addCostComponent(card, jsonObject);
        addPlacementComponents(card, jsonObject);
        addPlaymentRestrictionComponents(card, jsonObject);

        addActionComponent(card, jsonObject);
        addEventComponent(card, jsonObject);

        addBuildingComponents(card, jsonObject);
        addHeroComponent(card, jsonObject);
        addUnitComponent(card, jsonObject);
        addSpecialSettlementOrCityExpansionComponent(card, jsonObject);

        return card;
    }

    private void addSpecialSettlementOrCityExpansionComponent(Card card, JsonObject jsonObject) {
        String placement = getStringOrNull(jsonObject, "placement");
        if (placement != null && placement.equals("Settlement/city expansion")) {
            String name = card.getComponent(StandardCardInfoComponent.class).name;
            PermanentEffect permanentEffect = PermanentEffectFactory.createEffect(name, card);
            if (permanentEffect != null) {
                PermanentEffectComponent expansionComponent = new PermanentEffectComponent(permanentEffect);
                card.addComponent(expansionComponent);
            }
        }
    }

    private void addBuildingComponents(Card card, JsonObject jsonObject) {
        String typeString = getStringOrNull(jsonObject, "type");
        if (typeString != null && typeString.toLowerCase().contains("building")) {
            BuildingCardComponent buildingComponent = new BuildingCardComponent();
            card.addComponent(buildingComponent);

        }
    }

    private void addEventComponent(Card card, JsonObject jsonObject) {
        if (jsonObject.has("type") && !jsonObject.get("type").isJsonNull()) {
            String type = jsonObject.get("type").getAsString();
            if (type.equalsIgnoreCase("Event")) {
                String name = card.getComponent(StandardCardInfoComponent.class).name;
                DirectEffect effect = DirectEffectFactory.forCardName(name);
                if (effect == null) {
                    throw new UnsupportedOperationException("Unimplemented event effect parser for: " + name);
                }
                EventCardComponent eventComponent = new EventCardComponent(effect);
                card.addComponent(eventComponent);
            }
        }
    }

    /**
     * Adds center card component if applicable, to Road, Settlement, City cards
     * 
     * @param card
     * @param jsonObject
     */
    private void addCenterCardComponent(Card card, JsonObject jsonObject) {
        String name = getStringOrNull(jsonObject, "name");
        if (name != null) {
            switch (name) {
                case "Road":
                    RoadCardComponent roadComponent = new RoadCardComponent();
                    card.addComponent(roadComponent);
                    break;
                case "Settlement":
                    SettlementCardComponent settlementComponent = new SettlementCardComponent();
                    card.addComponent(settlementComponent);
                    break;
                case "City":
                    CityCardComponent cityComponent = new CityCardComponent();
                    card.addComponent(cityComponent);
                    break;
                // Add other center card components as needed
                default:
                    // No center card component for this card
                    break;
            }
        }
    }

    private void addPlaymentRestrictionComponents(Card card, JsonObject jsonObject) {

        if (jsonObject.has("Requires") && !jsonObject.get("Requires").isJsonNull()) {
            String requires = jsonObject.get("Requires").getAsString();
            // Create a simple playment restriction
            PlaymentRestriction restriction = createPlaymentRestriction(requires);
            if (restriction == null) {
                return;
            }
            PlaymentRestrictionComponent restrictionComponent = new PlaymentRestrictionComponent(restriction);
            card.addComponent(restrictionComponent);
        }

        if (jsonObject.has("oneOf") && !jsonObject.get("oneOf").isJsonNull()) {
            String oneOf = jsonObject.get("oneOf").getAsString();
            if (oneOf.equals("1x")) {
                PlaymentRestriction restriction = new PlaymentRestrictionOneOf();
                PlaymentRestrictionComponent restrictionComponent = new PlaymentRestrictionComponent(restriction);
                card.addComponent(restrictionComponent);
            }
        }

    }

    /**
     * Adds standard card information (name and description)
     */
    private void addStandardCardInfo(Card card, JsonObject json) {
        String name = getStringOrNull(json, "name");
        String cardText = getStringOrNull(json, "cardText");

        if (name != null) {
            StandardCardInfoComponent info = new StandardCardInfoComponent(
                    name,
                    cardText != null ? cardText : "",
                    getMatchingDrawDeckName(json));
            card.addComponent(info);
        }
    }

    private String getMatchingDrawDeckName(JsonObject json) {
        String name = getStringOrNull(json, "name");
        switch (name) {
            case "Road":
                return "RoadDeck";
            case "Settlement":
                return "SettlementDeck";
            case "City":
                return "CityDeck";
            // Add other cases as needed
            default:
                String placement = getStringOrNull(json, "placement");
                if (placement == "Region") {
                    return "RegionDeck";
                } else if (placement == "Event") {
                    return "EventDeck";
                } else {
                    return "DrawDeck";
                }
        }
    }

    /**
     * Adds cost component if the card has a cost
     */
    private void addCostComponent(Card card, JsonObject json) {
        if (json.has("cost") && !json.get("cost").isJsonNull()) {
            String costStr = json.get("cost").getAsString();
            CardSymbol[] costSymbols = parseCost(costStr);
            if (costSymbols.length > 0) {
                CostCardComponent cost = new CostCardComponent(costSymbols);
                card.addComponent(cost);
            }
        }
    }

    /**
     * Parses a cost string into CardSymbol array
     * Format: "ABWW" where each letter represents a resource:
     * B = Brick, G = Grain, L = Lumber, W = Wool (Pasture), O = Ore, A = Gold
     * Example: "ABWW" -> [CardSymbol(GOLD, 1), CardSymbol(BRICK, 1),
     * CardSymbol(PASTURE, 2)]
     */
    private CardSymbol[] parseCost(String costStr) {
        if (costStr == null || costStr.trim().isEmpty()) {
            return new CardSymbol[0];
        }

        // Count occurrences of each resource type
        java.util.Map<CardSymbolType, Integer> resourceCounts = new java.util.HashMap<>();

        for (char c : costStr.toCharArray()) {
            CardSymbolType type = parseResourceTypeFromChar(c);
            if (type != null) {
                resourceCounts.put(type, resourceCounts.getOrDefault(type, 0) + 1);
            }
        }

        // Convert to CardSymbol array
        List<CardSymbol> symbols = new ArrayList<>();
        for (java.util.Map.Entry<CardSymbolType, Integer> entry : resourceCounts.entrySet()) {
            symbols.add(new CardSymbol(entry.getKey(), entry.getValue()));
        }

        return symbols.toArray(new CardSymbol[0]);
    }

    /**
     * Parse resource type from a single character
     * B = Brick, G = Grain, L = Lumber, W = Wool (Pasture), O = Ore, A = Gold
     */
    private CardSymbolType parseResourceTypeFromChar(char c) {
        switch (Character.toUpperCase(c)) {
            case 'B':
                return CardSymbolType.BRICK;
            case 'G':
                return CardSymbolType.GRAIN;
            case 'L':
                return CardSymbolType.LUMBER;
            case 'W':
                return CardSymbolType.WOOL; // Wool = Pasture
            case 'O':
                return CardSymbolType.ORE;
            case 'A':
                return CardSymbolType.GOLD;
            default:
                return null;
        }
    }

    /**
     * Adds placement and playment restriction components if applicable
     */
    private void addPlacementComponents(Card card, JsonObject json) {
        if (json.has("placement") && !json.get("placement").isJsonNull()) {
            String placement = json.get("placement").getAsString();
            // Add PlacableCardComponent if it's placeable
            if (!placement.equalsIgnoreCase("Event") &&
                    !placement.equalsIgnoreCase("Action") && !placement.equalsIgnoreCase("Region")) {
                PlacementRestriction placable = getPlacementRestriction(placement, card);
                if (placable == null) {
                    return;
                }
                card.addComponent(new PlacableCardComponent(placable));
            }
        }

    }

    private PlacementRestriction getPlacementRestriction(String placement, Card card) {
        switch (placement) {
            case "Settlement/city":
                return new PlacementRestrictionSettlementOrCity();
            case "Center Card":
                if (card.hasComponent(RegionCardComponent.class)) {
                    return null;
                }
                if (card.hasComponent(RoadCardComponent.class)) {
                    return new PlaceMentRestrictionRoad();
                } else if (card.hasComponent(SettlementCardComponent.class)) {
                    return new PlaceMentRestricionSettlement();
                } else if (card.hasComponent(CityCardComponent.class)) {
                    return new PlaceMentResticionCity();
                } else {
                    throw new IllegalArgumentException(card.getComponent(StandardCardInfoComponent.class).name
                            + ": Center Card placement restriction requires Road, Settlement, or City component.");
                }
            default:
                throw new UnsupportedOperationException("Unimplemented placement restriction for: " + placement);
        }
    }

    /**
     * Creates a PlaymentRestriction from a requirement string
     */
    private PlaymentRestriction createPlaymentRestriction(String requires) {
        switch (requires) {
            case "Settlement previously built":
                return null; // No restriction, is PlacementRestriction
            case "Strength advantage":
                return null; // No restriction, handled by event effect
            case "PP":
                return null; // No restriction, handled by event effect
            case "Settlement built":
                return null; // No restriction, is PlacementRestriction
            case "Precisely 1 road between 2 towns":
                return null; // No restriction, is PlacementRestriction
            case "More trade ships":
                return null; // No restriction, handled by event effect
            case "Gold":
                return null; // No restriction, handled by event effect
            case "Storehouse /Abbey":
                return null; // No restriction, handled by event effect
            default:
                throw new UnsupportedOperationException("Unimplemented playment restriction for: " + requires);
        }
    }

    /**
     * Adds resource-related components (for regions)
     */
    private void addResourceComponents(Card card, JsonObject json) {
        String type = getStringOrNull(json, "type");
        String name = getStringOrNull(json, "name");

        if ("Region".equalsIgnoreCase(type) && name != null) {
            // Determine resource type from region name
            // Temporary setting the die to -1, fixed by postCardLoader
            CardSymbolType resourceType = getResourceTypeFromRegionName(name);
            if (resourceType != null) {
                RegionCardComponent resourceHolder = new RegionCardComponent(resourceType, -1);
                card.addComponent(resourceHolder);
            }
        }
    }

    /**
     * Get resource type from region name
     */
    private CardSymbolType getResourceTypeFromRegionName(String regionName) {
        if (regionName == null)
            return null;

        String name = regionName.toLowerCase();
        if (name.contains("gold"))
            return CardSymbolType.GOLD;
        if (name.contains("forest"))
            return CardSymbolType.LUMBER;
        if (name.contains("field"))
            return CardSymbolType.GRAIN;
        if (name.contains("pasture"))
            return CardSymbolType.WOOL;
        if (name.contains("hill"))
            return CardSymbolType.BRICK;
        if (name.contains("mountain"))
            return CardSymbolType.ORE;

        return null;
    }

    /**
     * Adds action component if the card has special actions
     */
    private void addActionComponent(Card card, JsonObject json) {
        if (json.has("cardText") && !json.get("cardText").isJsonNull()) {
            // If card has action type in JSON, add ActionCardComponent
            if (getStringOrNull(json, "type") != null &&
                    getStringOrNull(json, "type").equals("Action")) {
                String actionTypeText = getStringOrNull(json, "type");
                String cardName = getStringOrNull(json, "name");
                ActionType actionType = ActionType.valueOf(actionTypeText.toUpperCase());

                WhenPlayable whenPlayable = new WhenPlayable(parseActionCardIsPlayable(card));
                DirectEffect actionEffect = DirectEffectFactory.forCardName(cardName);
                if (actionEffect == null) {
                    throw new UnsupportedOperationException("Unimplemented action effect parser for: " + cardName);
                }
                ActionCardComponent action = new ActionCardComponent(actionType, whenPlayable, actionEffect);
                card.addComponent(action);
            }
        }
    }

    private String parseActionCardIsPlayable(Card card) {
        // Switch of special Cards, rest return default playable
        switch (card.getComponent(StandardCardInfoComponent.class).name) {
            // Cards that can be played during opponent's turn (defensive/reactive)
            case "Scout":
                return "WhenBuildingASettlement"; // When opponent builds a settlement
            // Cards played before dice roll
            case "Brigitta, the Wise Woman":
                return "BeforeRollingTheDice"; // Play before rolling production dice

            case "Sebastian the Itinerant Preacher":
                return "WhenEventRiotOccurs,WhenEventFeudOccurs,WhenEventFraternalFeudOccurs"; // Play when Riot event
                                                                                               // occurs

            case "Reiner the Herald":
                return "BeforeRollingTheDice"; // Play before rolling dice to determine Celebration

            default:
                return "OnYourTurn";
        }
    }

    /**
     * Adds hero component if applicable
     */
    private void addHeroComponent(Card card, JsonObject json) {
        String type = getStringOrNull(json, "type");
        // Add hero component for specific hero cards
        if (type != null && (type.toLowerCase().contains("hero"))) {
            HeroCardComponent hero = new HeroCardComponent();
            card.addComponent(hero);
        }
    }

    /**
     * Adds unit component for units
     */
    private void addUnitComponent(Card card, JsonObject json) {
        // Check if card has strength/defense stats or is a unit type
        String type = getStringOrNull(json, "type");
        if (type != null && type.toLowerCase().contains("unit")) {
            UnitCardComponent unit = new UnitCardComponent();
            card.addComponent(unit);
        }
    }

    // Helper methods

    private String getStringOrNull(JsonObject json, String key) {
        if (json.has(key) && !json.get(key).isJsonNull()) {
            return json.get(key).getAsString();
        }
        return null;
    }

    // Removed unused helpers getIntOrDefault and hasAnyField to avoid warnings
}
