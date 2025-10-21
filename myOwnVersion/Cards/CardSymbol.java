package myOwnVersion.Cards;

public class CardSymbol {
    private final int amount;
    private final CardSymbolType symbol;

    public CardSymbol(CardSymbolType symbol, int amount) {
        this.amount = amount;
        this.symbol = symbol;
    }

    public int getAmount() {
        return this.amount;
    }

    public CardSymbolType getCardSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return symbol.name() + "-" + amount;
    }

    
}
