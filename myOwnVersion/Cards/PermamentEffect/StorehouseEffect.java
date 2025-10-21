package myOwnVersion.Cards.PermamentEffect;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.BrigandImuneComponent;
import myOwnVersion.Cards.CardComponents.PlacableCardComponent;
import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.Principality.Position;

public class StorehouseEffect implements PermanentEffect {
    private final Card origin;

    public StorehouseEffect(Card origin) {
        this.origin = origin;
    }

    @Override
    public Card getOrigin() {
        return origin;
    }

    @Override
    public void restoreOriginalState(GameMaster gameMatster, GameState gameState) {
        Position pos = origin.getComponent(PlacableCardComponent.class).position;

        Position neighbor1 = new Position(pos.getColumn() + 1, pos.getRow());
        Position neighbor2 = new Position(pos.getColumn() - 1, pos.getRow());

        Card neighborCard1 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor1);
        Card neighborCard2 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor2);

        neighborCard1.removeComponent(BrigandImuneComponent.class);
        neighborCard2.removeComponent(BrigandImuneComponent.class);
    }

    @Override
    public void applyPermanentEffect(GameMaster gameMaster, GameState gameState) {
        Position pos = origin.getComponent(PlacableCardComponent.class).position;

        Position neighbor1 = new Position(pos.getColumn() + 1, pos.getRow());
        Position neighbor2 = new Position(pos.getColumn() - 1, pos.getRow());

        Card neighborCard1 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor1);
        Card neighborCard2 = gameState.getCurrentPlayer().getPrincipality().getCardAt(neighbor2);

        neighborCard1.addComponent(new BrigandImuneComponent());
    }

}
