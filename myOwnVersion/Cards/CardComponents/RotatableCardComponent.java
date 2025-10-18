package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardReward;

public class RotatableCardComponent implements CardComponent {
    public int curentFaceIndex;
    private final CardReward[] faces;

    public RotatableCardComponent(CardReward[] faces) {
        this.faces = faces;
        this.curentFaceIndex = 0;
    }

    public CardReward getCurrentFace() {
        return faces[curentFaceIndex];
    }
}