package myOwnVersion.GameTurn;

import java.util.List;

import myOwnVersion.GameMaster;
import myOwnVersion.PlayerCommunication.Player;
import myOwnVersion.PlayerCommunication.PlayerCommunication;

public class PlentifulHarvestBasic implements PlentifulHarvest {
    @Override
    public void run() {
        GameMaster gameMaster = GameMaster.getInstance();
        List<Player> players = gameMaster.getAllPlayers();
        PlayerCommunication pc = PlayerCommunication.getInstance();
        String prompt = "Plentiful Harvest: Choose a resource to receive:";
        for (Player player : players) {
            pc.recieveResourceQuestion(prompt, player, false);
        }
    }
}
