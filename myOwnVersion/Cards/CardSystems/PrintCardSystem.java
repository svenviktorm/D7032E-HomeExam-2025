package myOwnVersion.Cards.CardSystems;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.StandardCardInfoComponent;

public class PrintCardSystem extends CardSystem {

    public String printCard(Card card) {
        StandardCardInfoComponent info = card.getComponent(StandardCardInfoComponent.class);
        StringBuilder sb = new StringBuilder();
        sb.append("").append(info.name).append("");


        return sb.toString();
    }

}
