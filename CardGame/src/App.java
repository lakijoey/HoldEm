import static service.Deal.moneyUpload;
import static service.Deal.runGame;

public class App {

    public static void main(String[] args) {
        try {
            moneyUpload();
            runGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
