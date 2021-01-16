public class Card {
    private Symbols symbol;
    private int value;

    public Card(Symbols symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public Symbols getSymbol() {
        return symbol;
    }

    public String getSymbolForm() {
        switch (symbol) {
            case CLUBS:
                return "♣";
            case DIAMONDS:
                return "♦";
            case SPADES:
                return "♤";
            case HEARTS:
                return "♥";
            default:
                return symbol.name();
        }
    }

    public void setSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
