import java.util.Scanner;

public class Homeworkfive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();

        if (input.isEmpty()) {
            System.out.println("Вы не ввели символ");
            return;
        }

        char c = input.charAt(0);
        String keyboard = "qwertyuiopasdfghjklzxcvbnm";

        int index = keyboard.indexOf(c);
        if (index == -1) {
            System.out.println("Введённый символ не является буквой на клавиатуре");
            return;
        }

        int leftIndex = (index - 1 + keyboard.length()) % keyboard.length();
        System.out.println(keyboard.charAt(leftIndex));
    }
}