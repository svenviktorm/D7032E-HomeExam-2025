package myOwnVersion.Cards.CardSystems;

import myOwnVersion.Cards.Card;
import myOwnVersion.Cards.CardComponents.RotatableCardComponent;

public class RotateCardSystem extends CardSystem {

    /**
     * Rotates the card left, if card is already at maximum rotation it will not rotate and return false.
     * @param card the card is 
     * @return true if the rotation was succesfull
     */
    public boolean roateLeft(Card card) {
        if (!card.hasComponent(RotatableCardComponent.class)) {
            return false;
        }
        RotatableCardComponent rot = card.getComponent(RotatableCardComponent.class);
        if (rot.curentFaceIndex >= 3) {
            return false;
        } else {
            rot.curentFaceIndex ++;
            updateRewards(rot);
            return true;
        }
    }

    /**
     * Rotates the card right, if card already was at minimum rotation it will not rotate and return false.
     * @param card the card that is rotated.
     * @return true of the rotation was sucesfull
     */
    public boolean roateRight(Card card) {
        if (!card.hasComponent(RotatableCardComponent.class)) {
            return false;
        }
        RotatableCardComponent rot = card.getComponent(RotatableCardComponent.class);
        if (rot.curentFaceIndex <= 0) {
            return false;
        } else {
            rot.curentFaceIndex --;
            updateRewards(rot);
            return true;
        }
    }

    private void updateRewards(RotatableCardComponent rot) {
        rot.rewards = rot.faces.get(rot.curentFaceIndex);
    }
}
