package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardSymbol;
import java.util.ArrayList;
import java.util.List;

/**
 * A card component that can be rotated to show different faces, each with its own set of symbol rewards.
 */
public class RotatableCardComponent extends HasSymbolRewardCardComponent {
    public int curentFaceIndex;
    public final List<List<CardSymbol>> faces;

    public RotatableCardComponent(List<List<CardSymbol>> faces) {
        super(faces.get(0));
        this.faces = faces;
        this.curentFaceIndex = 0;
    }

}