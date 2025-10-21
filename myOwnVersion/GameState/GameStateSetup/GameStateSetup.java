package myOwnVersion.GameState.GameStateSetup;

import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.GameState.GameState;

public abstract class GameStateSetup {
    public abstract GameState setup(List<Card> cards);
}
