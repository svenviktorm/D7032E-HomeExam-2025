package myOwnVersion.Cards;

public class CardSymbolReward {
    private final int amount;
    private final CardSymbol symbol;

    public CardSymbolReward(CardSymbol symbol, int amount) {
        this.amount = amount;
        this.symbol = symbol;
    }

    public int getAmount() {
        return this.amount;
    }

    public CardSymbol getCardSymbol() {
        return this.symbol;
    }
}
