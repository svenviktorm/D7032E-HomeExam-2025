package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardSymbolType;

/**
 * A card component for every resource card, tied with ResourceCardCardSystem.
 */
public class ResourceCardCardComponent extends ResourceHoldingCardComponent {
    public final int productionDiceRoll;

    /**
     * Constructs a ResourceCardCardComponent with the specified resource type and production dice roll.
     *
     * @param resourceType      The type of resource the card holds.
     * @param productionDiceRoll The dice roll required for production.
     */
    public ResourceCardCardComponent(CardSymbolType resourceType, int productionDiceRoll) {
        super(resourceType);
        this.productionDiceRoll = productionDiceRoll;
    }

}
