import java.util.Scanner;

public class Homeworkthree {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваше имя: ");
        String username = scanner.nextLine();

        System.out.println("Привет, " + username);
    }
}
