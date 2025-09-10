import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner autoScannerQuantity = new Scanner(System.in);

        System.out.print("Введите количество автомобилей: ");
        int autoQuantity = Integer.parseInt(autoScannerQuantity.nextLine());

        List<Car> cars = new ArrayList<>();

        System.out.println("Введите данные автомобилей в формате number|model|color|mileage|cost :");
        for (int i = 0; i < autoQuantity; i++) {
            String line = autoScannerQuantity.nextLine();
            String[] parts = line.split("\\|");
            if(parts.length != 5) {
                System.out.println("Неверный формат данных. Попробуйте ещё раз.");
                i--;
                continue;
            }
            try {
                String number = parts[0];
                String model = parts[1];
                String color = parts[2];
                int mileage = Integer.parseInt(parts[3]);
                double cost = Double.parseDouble(parts[4]);
                cars.add(new Car(number, model, color, mileage, cost));
            } catch (NumberFormatException ex) {
                System.out.println("Ошибка преобразования пробега или цены. Попробуйте ещё раз.");
                i--;
            }
        }

        System.out.print("Введите цвет и пробег через запятую (например, Black, 0L): ");
        String colorAndMileageRaw = autoScannerQuantity.nextLine();
        String[] colorAndMileage = colorAndMileageRaw.replace("L", "").split(",\\s*");
        if(colorAndMileage.length != 2){
            System.out.println("Ошибка ввода цвета и пробега.");
            return;
        }
        String colorToFind = colorAndMileage[0];
        int mileageToFind;
        try {
            mileageToFind = Integer.parseInt(colorAndMileage[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования пробега.");
            return;
        }

        System.out.print("Введите диапазон цен через запятую (например, 700_000L, 800_000L): ");
        String costRangeRaw = autoScannerQuantity.nextLine();
        String[] costRangeStr = costRangeRaw.replace("L", "").replace("_", "").split(",\\s*");
        if(costRangeStr.length != 2){
            System.out.println("Ошибка ввода диапазона цен.");
            return;}
        double costFrom, costTo;
        try {
            costFrom = Double.parseDouble(costRangeStr[0]);
            costTo = Double.parseDouble(costRangeStr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования цен.");
            return;
        }

        System.out.print("Введите модель автомобиля для подсчёта средней стоимости (например, Toyota): ");
        String modelToFind = autoScannerQuantity.nextLine();

        List<String> numbers = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());

        System.out.println("\nНомера автомобилей с цветом '" + colorToFind + "' или пробегом " + mileageToFind + ":");
        if(numbers.isEmpty()){
            System.out.println("Нет совпадений.");
        } else {
            numbers.forEach(System.out::println);
        }

        long uniqueModelsCount = cars.stream()
                .filter(car -> car.getCost() >= costFrom && car.getCost() <= costTo)
                .map(Car::getModel)
                .distinct()
                .count();

        System.out.println("\nКоличество уникальных моделей с ценой от " + (int)costFrom + " до " + (int)costTo + ": " + uniqueModelsCount);

        OptionalDouble avgCost = cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .mapToDouble(Car::getCost)
                .average();

        System.out.println("\nСредняя стоимость модели '" + modelToFind + "': " +
                (avgCost.isPresent() ? String.format("%.2f", avgCost.getAsDouble()) : "нет данных"));

        cars.stream()
                .min(Comparator.comparingDouble(Car::getCost))
                .ifPresentOrElse(
                        car -> System.out.println("\nЦвет автомобиля с минимальной стоимостью: " + car.getColor()),
                        () -> System.out.println("\nНет данных для определения минимальной стоимости")
                );
    }

}