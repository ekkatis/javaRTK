import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Homework08 {
    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        return new HashSet<>(list);
    }

    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("яблоко");
        fruits.add("банан");
        fruits.add("яблоко");
        fruits.add("апельсин");
        fruits.add("банан");

        Set<String> uniqueFruits = getUniqueElements(fruits);

        System.out.println(uniqueFruits);
    }
}