package myOwnVersion.Cards.PermamentEffect;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbolType;

public class PermanentEffectFactory {

    public static PermanentEffect createEffect(String name, Card origin) {
        switch (name) {
            case "Toll Bridge":
                return new TollBridgeEffect(origin);
            case "Storehouse":
                return new StorehouseEffect(origin);
            case "Iron Foundry":
                return new ProductionBoosterEffect(CardSymbolType.ORE, origin);
            case "Grain Mill":
                return new ProductionBoosterEffect(CardSymbolType.GRAIN, origin);
            case "Lumber Camp":
                return new ProductionBoosterEffect(CardSymbolType.LUMBER, origin);
            case "Brick Factory":
                return new ProductionBoosterEffect(CardSymbolType.BRICK, origin);
            case "Weaver's Shop":
                return new ProductionBoosterEffect(CardSymbolType.WOOL, origin);
            case "Abbey":
                return null; // has no permanent effect
            case "Marketplace":
                return new MarketplaceEffect(origin);
            case "Parish Hall":
                return new ParishHallEffect(origin);
            case "Large Trade Ship":
                return new LargeTradeShipEffect(origin);
            case "Gold Ship":
                return new TwoToOneTradeEffect(CardSymbolType.GOLD, origin);
            case "Ore Ship":
                return new TwoToOneTradeEffect(CardSymbolType.ORE, origin);
            case "Grain Ship":
                return new TwoToOneTradeEffect(CardSymbolType.GRAIN, origin);
            case "Lumber Ship":
                return new TwoToOneTradeEffect(CardSymbolType.LUMBER, origin);
            case "Brick Ship":
                return new TwoToOneTradeEffect(CardSymbolType.BRICK, origin);
            case "Wool Ship":
                return new TwoToOneTradeEffect(CardSymbolType.WOOL, origin);
            case "Austin":
                return null; // has no permanent effect
            case "Harald":
                return null; // has no permanent effect
            case "Inga":
                return null; // has no permanent effect
            case "Osmund":
                return null; // has no permanent effect
            case "Candamir":
                return null; // has no permanent effect
            case "Siglind":
                return null; // has no permanent effect
            default:
                throw new IllegalArgumentException("Unknown Permanent Effect: " + name);
        }
    }
}
