package myOwnVersion.Cards.PermamentEffect;
import myOwnVersion.GameMaster;
import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.GameState;

public class LargeTradeShipEffect implements PermanentEffect {
    private final Card origin;

    public LargeTradeShipEffect(Card origin) {
        this.origin = origin;
    }

    @Override
    public Card getOrigin() {
        return origin;
    }

    @Override
    public void restoreOriginalState(GameMaster gameMatster, GameState gameState) {
        // No ongoing effect to restore
    }

}
