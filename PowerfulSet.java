import java.util.HashSet;
import java.util.Set;

public class PowerfulSet {

    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    // Пример использования
    public static void main(String[] args) {
        PowerfulSet ps = new PowerfulSet();

        Set<Integer> set1 = Set.of(1, 2, 3);
        Set<Integer> set2 = Set.of(0, 1, 2, 4);

        System.out.println("Intersection: " + ps.intersection(set1, set2));  // {1, 2}
        System.out.println("Union: " + ps.union(set1, set2));                // {0, 1, 2, 3, 4}
        System.out.println("Relative Complement: " + ps.relativeComplement(set1, set2)); // {3}
    }
}
