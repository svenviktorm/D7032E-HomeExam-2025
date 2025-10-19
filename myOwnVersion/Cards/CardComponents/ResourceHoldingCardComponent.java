package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardSymbol;
import myOwnVersion.Cards.CardSymbolReward;
import java.util.ArrayList;
import java.util.List;

public class ResourceHoldingCardComponent extends RotatableCardComponent {
    public final CardSymbol resourceType;

    public ResourceHoldingCardComponent(CardSymbol resourceType) {
        this.resourceType = resourceType;
        ArrayList<ArrayList<CardSymbolReward>> resources = new ArrayList<>();
        
        //first face
        ArrayList<CardSymbolReward> firstFace = new ArrayList<>();

        //second face
    }

}