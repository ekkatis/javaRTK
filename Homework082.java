import java.util.Arrays;
import java.util.Scanner;

public class Homework082 {

        public static boolean areAnagrams(String s, String t) {
            s = s.toLowerCase();
            t = t.toLowerCase();

            s = s.replaceAll("\\s+", "");
            t = t.replaceAll("\\s+", "");

            char[] sArray = s.toCharArray();
            char[] tArray = t.toCharArray();

            if (sArray.length != tArray.length) {
                return false;
            }

            Arrays.sort(sArray);
            Arrays.sort(tArray);

            return Arrays.equals(sArray, tArray);
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите первую строку: ");
            String s = scanner.nextLine();

            System.out.print("Введите вторую строку: ");
            String t = scanner.nextLine();

            System.out.println(areAnagrams(s, t));
        }
}
