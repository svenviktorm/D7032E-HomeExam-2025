package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardHandeler;
import myOwnVersion.Cards.CardSymbolType;

public class DoubleRegionCardComponent extends RegionCardComponent{

    public DoubleRegionCardComponent(CardSymbolType regionType, int productionDie, int startingValue) {
        super(regionType, productionDie);
        for (int i = 0; i < startingValue - 1; i++) {
            this.curentFaceIndex++;
            this.rewards = this.faces.get(this.curentFaceIndex);
        }
    }

}
