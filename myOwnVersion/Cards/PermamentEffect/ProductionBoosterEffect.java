package myOwnVersion.Cards.PermamentEffect;

import javax.swing.plaf.synth.Region;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardComponents.DoubleRegionCardComponent;
import myOwnVersion.Cards.CardComponents.PlacableCardComponent;
import myOwnVersion.Cards.CardComponents.RegionCardComponent;
import myOwnVersion.Cards.CardSystems.ResourceCardSystem;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Position;

public class ProductionBoosterEffect implements PermanentEffect {
    private final CardSymbolType boostedSymbol;
    private Card origin;
    private RegionCardComponent regionComp1;
    private RegionCardComponent regionComp2;

    public ProductionBoosterEffect(CardSymbolType boostedSymbol, Card origin) {
        this.boostedSymbol = boostedSymbol;
        this.origin = origin;
    }

    @Override
    public Card getOrigin() {
        return origin;
    }

    @Override
    public void restoreOriginalState(myOwnVersion.GameMaster gameMatster, myOwnVersion.GameState.GameState gameState) {

        ResourceCardSystem resourceSystem = myOwnVersion.Cards.CardHandeler.getInstance()
                .getCardSystem(ResourceCardSystem.class);
        Position pos = origin.getComponent(PlacableCardComponent.class).position;

        Position neighbor1 = new Position(pos.getColumn() + 1, pos.getRow());
        Position neighbor2 = new Position(pos.getColumn() - 1, pos.getRow());

        Card neighborCard1 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor1);
        Card neighborCard2 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor2);

        if (neighborCard1.hasComponent(DoubleRegionCardComponent.class)) {
            DoubleRegionCardComponent regionComp1 = neighborCard1.getComponent(DoubleRegionCardComponent.class);
            if (regionComp1.resourceType == this.boostedSymbol) {

                int amount1 = regionComp1.rewards.get(0).getAmount();
                neighborCard1.removeComponent(DoubleRegionCardComponent.class);
                neighborCard1
                        .addComponent(
                                new RegionCardComponent(regionComp1.resourceType, regionComp1.productionDiceRoll));
                resourceSystem.produceLocal(neighborCard1, amount1);
            }
        }

        if (neighborCard2.hasComponent(DoubleRegionCardComponent.class)) {
            DoubleRegionCardComponent regionComp2 = neighborCard2.getComponent(DoubleRegionCardComponent.class);
            if (regionComp2.resourceType == this.boostedSymbol) {

                int amount2 = regionComp2.rewards.get(0).getAmount();
                neighborCard2.removeComponent(DoubleRegionCardComponent.class);
                neighborCard2
                        .addComponent(
                                new RegionCardComponent(regionComp2.resourceType, regionComp2.productionDiceRoll));
                resourceSystem.produceLocal(neighborCard2, amount2);
            }
        }

    }

    @Override
    public void applyPermanentEffect(GameMaster gameMaster, GameState gameState) {
        Position pos = origin.getComponent(PlacableCardComponent.class).position;

        Position neighbor1 = new Position(pos.getColumn() + 1, pos.getRow());
        Position neighbor2 = new Position(pos.getColumn() - 1, pos.getRow());

        Card neighborCard1 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor1);
        Card neighborCard2 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor2);

        RegionCardComponent regionComp1 = neighborCard1.getComponent(RegionCardComponent.class);
        RegionCardComponent regionComp2 = neighborCard2.getComponent(RegionCardComponent.class);

        this.regionComp1 = regionComp1;
        this.regionComp2 = regionComp2;

        int amount1 = regionComp1.rewards.get(0).getAmount();
        int amount2 = regionComp2.rewards.get(0).getAmount();

        int productionRoll1 = regionComp1.productionDiceRoll;
        int productionRoll2 = regionComp2.productionDiceRoll;

        CardSymbolType resourceType1 = regionComp1.resourceType;
        CardSymbolType resourceType2 = regionComp2.resourceType;

        if (resourceType1 == this.boostedSymbol) {
            neighborCard1.removeComponent(RegionCardComponent.class);
            neighborCard1
                    .addComponent(new DoubleRegionCardComponent(regionComp1.resourceType, productionRoll1, amount1));
        }

        if (resourceType2 == this.boostedSymbol) {
            neighborCard2.removeComponent(RegionCardComponent.class);
            neighborCard2
                    .addComponent(new DoubleRegionCardComponent(regionComp2.resourceType, productionRoll2, amount2));
        }

    }

}
