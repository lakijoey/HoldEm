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
