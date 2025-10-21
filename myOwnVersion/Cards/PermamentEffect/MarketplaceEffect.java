package myOwnVersion.Cards.PermamentEffect;

import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.GameState;

public class MarketplaceEffect implements PermanentEffect {
    private final Card origin;

    public MarketplaceEffect(Card origin) {
        this.origin = origin;
    }

    @Override
    public Card getOrigin() {
        return origin;
    }

    @Override
    public void restoreOriginalState(GameMaster gameMatster, GameState gameState) {
        // No permanent state to restore for MarketplaceEffect
    }

    @Override
    public void applyPermanentEffect(GameMaster gameMaster, GameState gameState) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPermanentEffect'");
    }

}
