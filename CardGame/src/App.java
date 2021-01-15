public class App {

    public static void main(String[] args) {
        Deal.moneyUpload();
        try {
            Deal.runGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
