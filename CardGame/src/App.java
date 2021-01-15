public class App {

    public static void main(String[] args) {
        Deal.moneyUpload();
        try {
            Deal.aGame(Deck.myDeck());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
