import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

public class Tests {

    @Test
    public void drawStartingCardsTest() {
        Server server = null;
        try {
            server = gameSetup("1 \n 1 \n 1 \n","2 \n 2 \n 1 \n");
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
        Player player1 = server.players.get(0);
        Player player2 = server.players.get(1);
        assert(Card.drawStack3.size() == Card.drawStack4.size());
        assert(Card.drawStack2.size()+2 == Card.drawStack3.size());
        assert(Card.drawStack1.size()+2 == Card.drawStack2.size());
        assert((player1.handSize() == player2.handSize()) && (player1.handSize() == 3));
        System.out.println("drawstack3.size(): "+Card.drawStack3.size());
    }


     @Test
    public void cardLoadTest() {
        System.out.println("Card Load Test");
        try {
            Card.loadBasicCards("OriginalGameWithtest\\cards.json");
        } catch (Exception e) {
            assert(true);
            e.printStackTrace();
        }
        

        Vector<Card> allCards = new Vector<Card>();
        allCards.addAll(Card.roads);
        allCards.addAll(Card.settlements);
        allCards.addAll(Card.cities);
        allCards.addAll(Card.regions);
        allCards.addAll(Card.events);
        allCards.addAll(Card.drawStack1);
        allCards.addAll(Card.drawStack2);
        allCards.addAll(Card.drawStack3);
        allCards.addAll(Card.drawStack4);

        // Check total number of cards
        System.out.println(" total: "+allCards.size());
        assert(allCards.size() == 94);

        // Check Event cards
        System.out.println(" events: "+Card.events.size());
        assert(Card.events.size() == 9);

        // Check Center Cards
        var centerCards = Card.extractCardsByAttribute(allCards, "placement", "Center Card");
        System.out.println(" center: "+centerCards.size());
        assert(centerCards.size() == 49);

        // Check Basic Cards
        Vector<Card> basicCards = new Vector<Card>();
        for (var card : allCards) {
            if(card.theme.contains("Basic")) {
                basicCards.add(card);
            }
        }
        assert(basicCards.size() == 36+Card.events.size());

        // Check region cards
        var regionCards = Card.regions;
        System.out.println(" regions: "+regionCards.size());
        assert(regionCards.size() == 24);

        // Check settlement cards
        System.out.println(" settlements: "+Card.settlements.size());
        assert(Card.settlements.size() == 9);

        // Cehck city cards
        System.out.println(" cities: "+Card.cities.size());
        assert(Card.cities.size() == 7);

        // Check road cards
        System.out.println(" roads: "+Card.roads.size());
        assert(Card.roads.size() == 9);
    }
    @Test
    public void remainingRegionCardsSetupTest() {
        Server server = null;
        try {
            server = gameSetup("1 \n 1 \n 1 \n","1 \n 1 \n 1 \n");
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
        Vector<Card> actualRegionCards = (Vector<Card>) Card.regions.clone();

        // print out all region cards for visual inspection
        for (var card : actualRegionCards) {
            System.out.println("Region Card: " + card.name + " with dice roll " + card.diceRoll);
        }

        // Generate expected region cards
        Vector<Card> expectedRegionCards = new Vector<Card>();
        int[][] regionDice = { {3, 1}, {4, 2}, {5, 1}, {6, 2}, {6, 5}, {3, 2} };
        String[] regionNames = { "Field", "Mountain", "Hill", "Forest", "Pasture", "Gold Field" };
        for (int i = 0; i < regionNames.length; i++) {
            Card card1 = new Card(regionNames[i], null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            card1.diceRoll = regionDice[i][0];
            Card card2 = new Card(regionNames[i], null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            card2.diceRoll = regionDice[i][1];
            expectedRegionCards.add(card1);
            expectedRegionCards.add(card2);
        }
        // Compare expected with actual Region cards
        assert(expectedRegionCards.size() == actualRegionCards.size());
        while (expectedRegionCards.size() > 0) {
            Card expectedCard = expectedRegionCards.remove(0);
            boolean found = false;
            for (int j = 0; j < actualRegionCards.size(); j++) {
                Card actualCard = actualRegionCards.get(j);
                if (expectedCard.name.equals(actualCard.name) && expectedCard.diceRoll == actualCard.diceRoll) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Missing region card: " + expectedCard.name + " with dice roll " + expectedCard.diceRoll);
                assert(false);
            }
            
        }

        // checking if shuffle worked by ensuring order is different
        server = null;
        try {
            server = gameSetup("1 \n 1 \n 1 \n","1 \n 1 \n 1 \n");
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
        Vector<Card> newCards = (Vector<Card>) Card.regions.clone();
        // loop through and check if at least one card is in a different position
        for (int i = 0; i < actualRegionCards.size(); i++) {
            if (!actualRegionCards.get(i).name.equals(newCards.get(i).name) ||
                actualRegionCards.get(i).diceRoll != newCards.get(i).diceRoll) {
                System.out.println("Shuffle verified: Card at position " + i + " is different.");
                return; // test passed
            }
        }
        assert(false); // if we reach here, shuffle failed
    }

    @Test
    public void eventCardsTest() {
        Server server = null;
        try {
            server = gameSetup("","");
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
        Vector<Card> eventCards = (Vector<Card>) Card.events.clone();
        //print out eventCards
        for (Card card : eventCards) {
            System.out.println("name: "+card.name);
        }
        System.out.println(eventCards.get(eventCards.size()-4).name);
        assert(eventCards.get(eventCards.size()-4).name.equals("Yule"));
    }


    @Test
    public void basicCardSetupTest() {
        Server server = null;
        try {
            server = gameSetup("","");
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
        Vector<Card> drawStack1 = (Vector<Card>) Card.drawStack1.clone();
        Vector<Card> drawStack2 = (Vector<Card>) Card.drawStack2.clone();
        Vector<Card> drawStack3 = (Vector<Card>) Card.drawStack3.clone();
        Vector<Card> drawStack4 = (Vector<Card>) Card.drawStack4.clone();

        // map of expected counts of card types
        var expectedCounts = new java.util.HashMap<String, Integer>();
        expectedCounts.put("Action", 9);
        expectedCounts.put("Settlement/city", 27);

        // assert size
        int totalCards = drawStack1.size() + drawStack2.size() + drawStack3.size() + drawStack4.size();
        assert(totalCards == expectedCounts.get("Action") + expectedCounts.get("Settlement/city"));
    
        // checking actual card placements
        for (Vector<Card> stack : new Vector[]{drawStack1, drawStack2, drawStack3, drawStack4}) {
            for (Card card : stack) {
                if (expectedCounts.containsKey(card.placement)) {
                    expectedCounts.put(card.placement, expectedCounts.get(card.placement) - 1);
                }
            }
        }
        assert(expectedCounts.get("Action") == 0);
        assert(expectedCounts.get("Settlement/city") == 0);

        // testing shuffle by ensuring order is different
        server = null;
        try {
            server = gameSetup("","");
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
        Vector<Card> newDrawStack1 = (Vector<Card>) Card.drawStack1.clone();
        for (int i = 0; i < drawStack1.size(); i++) {
            if (!drawStack1.get(i).name.equals(newDrawStack1.get(i).name)) {
                System.out.println("Shuffle verified: Card at position " + i + " in Draw Stack 1 is different.");
                return; // test passed
            }
        }
        assert(false); // if we reach here, shuffle failed
    }

    @Test
    public void principalitySetup() {
        Server server = null;
        try {
            server = gameSetup("1 \n 1 \n 1 \n","1 \n 1 \n 1 \n");
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
        List<Player> players = server.players;

        // Load the cards so all cards are available
        try {
            Card.loadBasicCards("OriginalGameWithtest\\cards.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create two expected 5x5 principalities
        List<List<Card>> expectedPrincipalityRed = createEmptyPrincipality();
        List<List<Card>> expectedPrincipalityBlue = createEmptyPrincipality();
        
        var roads = Card.roads;
        var settlements = Card.settlements;
        var regions = Card.regions;
        
        //adding whats in both  principalities
        expectedPrincipalityRed.get(2).set(1, Card.popCardByName(settlements, "Settlement"));
        expectedPrincipalityRed.get(2).set(2, Card.popCardByName(roads, "Road"));
        expectedPrincipalityRed.get(2).set(3, Card.popCardByName(settlements, "Settlement"));
        
        expectedPrincipalityBlue.get(2).set(1, Card.popCardByName(settlements, "Settlement"));
        expectedPrincipalityBlue.get(2).set(2, Card.popCardByName(roads, "Road"));
        expectedPrincipalityBlue.get(2).set(3, Card.popCardByName(settlements, "Settlement"));

        //adding whats unique in red principality
        Card forestRed = Card.popCardByName(Card.regions, "Forest");
        Card forestBlue = Card.popCardByName(Card.regions, "Forest");
        forestRed.diceRoll = 2;
        forestBlue.diceRoll = 3;
        forestBlue.regionProduction = 1;
        forestRed.regionProduction = 1;
        expectedPrincipalityRed.get(1).set(0, forestRed);
        expectedPrincipalityBlue.get(1).set(0, forestBlue);

        Card goldRed = Card.popCardByName(Card.regions, "Gold Field");
        Card goldBlue = Card.popCardByName(Card.regions, "Gold Field");
        goldRed.diceRoll = 1;
        goldBlue.diceRoll = 4;
        goldRed.regionProduction = 0;
        goldBlue.regionProduction = 0;
        expectedPrincipalityRed.get(1).set(2, goldRed);
        expectedPrincipalityBlue.get(1).set(2, goldBlue);

        Card fieldRed = Card.popCardByName(Card.regions, "Field");
        Card fieldBlue = Card.popCardByName(Card.regions, "Field");
        fieldRed.diceRoll = 6;
        fieldBlue.diceRoll = 5;
        fieldRed.regionProduction = 1;
        fieldBlue.regionProduction = 1;
        expectedPrincipalityRed.get(1).set(4, fieldRed);
        expectedPrincipalityBlue.get(1).set(4, fieldBlue);

        Card clayRed = Card.popCardByName(Card.regions, "Hill");
        Card clayBlue = Card.popCardByName(Card.regions, "Hill");
        clayRed.diceRoll = 3;
        clayBlue.diceRoll = 2;
        clayRed.regionProduction = 1;
        clayBlue.regionProduction = 1;
        expectedPrincipalityRed.get(3).set(0, clayRed);
        expectedPrincipalityBlue.get(3).set(0, clayBlue);

        Card patureRed = Card.popCardByName(Card.regions, "Pasture");
        Card patureBlue = Card.popCardByName(Card.regions, "Pasture");
        patureRed.diceRoll = 4;
        patureBlue.diceRoll = 1;
        patureRed.regionProduction = 1;
        patureBlue.regionProduction = 1;
        expectedPrincipalityRed.get(3).set(2, patureRed);
        expectedPrincipalityBlue.get(3).set(2, patureBlue);

        Card mountainRed = Card.popCardByName(Card.regions, "Mountain");
        Card mountainBlue = Card.popCardByName(Card.regions, "Mountain");
        mountainRed.diceRoll = 5;
        mountainBlue.diceRoll = 6;
        mountainRed.regionProduction = 1;
        mountainBlue.regionProduction = 1;
        expectedPrincipalityRed.get(3).set(4, mountainRed);
        expectedPrincipalityBlue.get(3).set(4, mountainBlue);

        Player player1 = players.get(0);
        Player player2 = players.get(1);
        System.out.println("player1: "+player1.printPrincipality());
        System.out.println("player2: "+player2.printPrincipality());
        // print out both principalities for visual inspection
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print((expectedPrincipalityRed.get(i).get(j) != null ? expectedPrincipalityRed.get(i).get(j).name : "Empty") + "\t");
                //and the cards regionProduction
                System.out.print((expectedPrincipalityRed.get(i).get(j) != null ? expectedPrincipalityRed.get(i).get(j).regionProduction : " ") + "\t");
                //and the cards diceRoll
                System.out.print((expectedPrincipalityRed.get(i).get(j) != null ? " | "+expectedPrincipalityRed.get(i).get(j).diceRoll : " ") + "\t");
            }
            System.out.println();
        }

        // Basic assertion that principalities are different
        assert(player1.principality != player2.principality);
        
        // Compare the principalities with expected ones
        if(checkPrincipalityEquals(player1.principality, expectedPrincipalityBlue) ||
           checkPrincipalityEquals(player1.principality, expectedPrincipalityRed)) {
            System.out.println("Player 1 principality matches expected configuration.");
        } else {
            System.err.println("Player 1 principality does not match expected configuration.");
            assert(false);
        }
        if(checkPrincipalityEquals(player2.principality, expectedPrincipalityBlue) ||
           checkPrincipalityEquals(player2.principality, expectedPrincipalityRed)) {
            System.out.println("Player 2 principality matches expected configuration.");
        } else {
            System.err.println("Player 2 principality does not match expected configuration.");
            assert(false);
        }
    }

    private List<List<Card>> createEmptyPrincipality() {
        List<List<Card>> principality = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Card> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                row.add(null);  // Initialize with null cards
            }
            principality.add(row);
        }
        return principality;
    }

    private boolean checkPrincipalityEquals(List<List<Card>> expected, List<List<Card>> actual) {
        int productionoffset = 1;
        if (expected.size() != actual.size()) {
            System.out.println("Different row counts");
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i).size() != actual.get(i).size()) {
                System.out.println("Row " + i + " has different column counts");
                return false;
            }
            for (int j = 0; j < expected.get(i).size(); j++) {
                Card expectedCard = expected.get(i).get(j);
                Card actualCard = actual.get(i).get(j);
                
                if (expectedCard == null) {
                    if (actualCard != null) {
                        System.out.println("Card mismatch at (" + i + "," + j + "): expected null, got " + actualCard.name);
                        return false;
                    }
                } else {
                    if (!(expectedCard.name.equals(actualCard.name)) || (expectedCard.diceRoll != actualCard.diceRoll) || (expectedCard.regionProduction != actualCard.regionProduction)) {
                        if (actualCard.regionProduction == expectedCard.regionProduction+1 ||actualCard.regionProduction+1 == expectedCard.regionProduction ) {
                            // one tile should always be off by one in regionProduction.
                            productionoffset = productionoffset -1;
                        } else {
                            System.out.println("Card mismatch at (" + i + "," + j + "): expected " + expectedCard.name + " (Dice: " + expectedCard.diceRoll + ", Prod: " + expectedCard.regionProduction + "), got " + (actualCard != null ? actualCard.name + " (Dice: " + actualCard.diceRoll + ", Prod: " + actualCard.regionProduction + ")" : "null"));
                            return false;
                        }
                        // Compare by name, diceRoll, and regionProduction for more detailed check
                        
                        if (productionoffset < 0) {
                            System.out.println("Card mismatch at (" + i + "," + j + "): expected " + expectedCard.name + " (Dice: " + expectedCard.diceRoll + ", Prod: " + expectedCard.regionProduction + "), got " + (actualCard != null ? actualCard.name + " (Dice: " + actualCard.diceRoll + ", Prod: " + actualCard.regionProduction + ")" : "null"));
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public Server gameSetup(String serverInput, String clientInput) throws Exception {
        Card.loadBasicCards("OriginalGameWithtest\\cards.json");

        // Set up input streams for both server and client
        ByteArrayInputStream serverInputStream = new ByteArrayInputStream(serverInput.getBytes());
        ByteArrayInputStream clientInputStream = new ByteArrayInputStream(clientInput.getBytes());

        // Create server and client instances
        Server hostServer = new Server();
        Server clientServer = new Server();

        // Create threads for server and client
        Thread serverThread = new Thread(() -> {
            try {
                System.setIn(serverInputStream);
                hostServer.start(false);
                hostServer.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread clientThread = new Thread(() -> {
            try {
                System.setIn(clientInputStream);
                clientServer.runClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        serverThread.start();
        Thread.sleep(1000); // Give server time to start
        clientThread.start();

        // Wait for both threads to complete or timeout
        try {
            serverThread.join(5000);
            clientThread.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hostServer;
    }
}
