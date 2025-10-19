package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardSymbol;

public class CostCardComponent implements CardComponent {
    public final CardSymbol[] cost;

    public CostCardComponent(CardSymbol[] cost) {
        this.cost = cost;
    }
}