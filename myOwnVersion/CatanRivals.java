package myOwnVersion;

import java.util.List;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardLoader.BasicCardLoaderSetup;
import myOwnVersion.Cards.CardLoader.BasicGamePostCardLoader;
import myOwnVersion.Cards.CardLoader.CardLoaderSetup;
import myOwnVersion.Cards.CardSystems.PrintCardSystem;
import myOwnVersion.GameState.BasicGameState;
import myOwnVersion.GameState.GameStateSetup.BasicGameStateSetup;
import myOwnVersion.GameState.GameStateSetup.GameStateSetup;

public class CatanRivals {

    public static void main(String[] args) {
        System.out.println("Welcome to Catan Rivals!");
        CardLoaderSetup setupLoader = new BasicCardLoaderSetup(new BasicGamePostCardLoader(), new BasicGameStateSetup());

        BasicGameState gameState = (BasicGameState) setupLoader.setup("Basic");
        System.out.println(gameState.toString());
        gameState.getAllPrincipalities().get(0).getCardCount();
    }
}