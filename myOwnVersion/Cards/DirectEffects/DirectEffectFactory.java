package myOwnVersion.Cards.DirectEffects;

public class DirectEffectFactory {
    private DirectEffectFactory() {}

    public static DirectEffect forCardName(String name) {
        if (name == null) return null;
        switch (name) {
            case "Brigitta, the Wise Woman":
                return new BrigittaWiseWomanEffect();
            case "Goldsmith":
                return new GoldsmithEffect();
            case "Merchant Caravan":
                return new MerchantCaravanEffect();
            case "Relocation":
                return new RelocationEffect();
            case "Scout":
                return new ScoutEffect();
            case "Feud":
                return new FeudEffect();
            case "Fraternal Feuds":
                return new FraternalFeudEffect();
            case "Invention":
                return new InventionEffect();
            case "Trade Ships Race":
                return new TradeShipRaceEffect();
            case "Traveling Merchant":
                return new TravelingMerchantEffect();
            case "Year of Plenty":
                return new YearOfPlentyEffect();
            case "Yule":
                return new YuleEffect();
            default:
                return null; // Unknown; caller can handle as unsupported
        }
    }
}
