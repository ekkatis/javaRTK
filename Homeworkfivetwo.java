import java.util.Scanner;


public class Homeworkfivetwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        int count = 0;

        int n = line.length();

        for (int i = 0; i < n; i++) {
            if (i + 4 < n) {
                if (line.charAt(i) == '>' &&
                    line.charAt(i + 1) == '>' &&
                    line.charAt(i + 2) == '-' &&
                    line.charAt(i + 3) == '-' &&
                    line.charAt(i + 4) == '>') {
                    count++;
                }
            }
            if (i + 4 < n) {
                if (line.charAt(i) == '<' &&
                        line.charAt(i + 1) == '-' &&
                        line.charAt(i + 2) == '-' &&
                        line.charAt(i + 3) == '<' &&
                        line.charAt(i + 4) == '<') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
