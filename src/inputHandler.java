import java.util.Scanner;

public class InputHandler {
    public static int getInput(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(message);
        int input = scanner.nextInt();

        return input;
    }

    public static int getInputInRange(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(message);
            int input = scanner.nextInt();

            if (input < min || input > max) {
                System.out.println(min + "〜" + max + "の範囲で入力してください。");
            } else {
                return input;
            }
        }
    }
}