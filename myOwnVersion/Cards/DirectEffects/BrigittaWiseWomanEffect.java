package myOwnVersion.Cards.DirectEffects;

import myOwnVersion.GameState.GameState;
import myOwnVersion.GameTurn.DiceRoller;
import myOwnVersion.GameTurn.FixedProductionDiceRoller;
import myOwnVersion.GameTurn.GameTurn;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class BrigittaWiseWomanEffect implements DirectEffect {
    @Override
    public void applyEffect(GameState gameState, GameTurn gameTurn) {
        PlayerCommunication playerCommunication = PlayerCommunication.getInstance();
        Player currentPlayer = gameTurn.getCurrentPlayer();
        String prompt = "Brigitta, the Wise Woman: Choose the result of the production die roll [1-6]";
        String acceptedResponseRegex = "^[1-6]$";
        String response = playerCommunication.askPlayerQuestion(prompt, currentPlayer, false, acceptedResponseRegex);
        int determinedValue = Integer.parseInt(response);

        DiceRoller diceRoller = new FixedProductionDiceRoller(determinedValue);

        gameTurn.overrideMethod(DiceRoller.class, diceRoller);
    }
}
