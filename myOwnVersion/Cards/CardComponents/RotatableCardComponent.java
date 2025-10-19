package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardSymbolReward;
import java.util.ArrayList;
import java.util.List;

public class RotatableCardComponent extends HasSymbolRewardCardComponent {
    public int curentFaceIndex;
    public final List<List<CardSymbolReward>> faces;

    public RotatableCardComponent(List<List<CardSymbolReward>> faces) {
        super(faces.get(0));
        this.faces = faces;
        this.curentFaceIndex = 0;
    }

}