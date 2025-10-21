package myOwnVersion.Cards.CardLoader;

import myOwnVersion.GameState.GameState;
import myOwnVersion.GameState.GameStateSetup.GameStateSetup;

public abstract class CardLoaderSetup {
    protected final PostCardLoader postCardLoader;
    protected final GameStateSetup gameStateSetup;

    //constructor method
    public CardLoaderSetup(PostCardLoader postCardLoader, GameStateSetup gameStateSetup) {
        this.postCardLoader = postCardLoader;
        this.gameStateSetup = gameStateSetup;
    }

    /**
     * Sets up the game state based on the provided theme.
     * Goes Through the steps of loading cards, processing them, and setting up the game state.
     * @param theme
     * @return
     */
    public abstract GameState setup(String theme);
}
