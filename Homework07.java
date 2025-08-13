import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Homework07 {

    static class Person {
        private String name;
        private double money;
        private List<Product> products;

        public Person(String name, double money) {
            setName(name);
            setMoney(money);
            this.products = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Имя не может быть пустым");
            }
            if (name.trim().length() < 3) {
                throw new IllegalArgumentException("Имя не может быть короче 3 символов");
            }
            this.name = name.trim();
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            if (money < 0) {
                throw new IllegalArgumentException("Деньги не могут быть отрицательным числом");
            }
            this.money = money;
        }

        public List<Product> getProducts() {
            return products;
        }

        public boolean buyProduct(Product product) {
            double priceToPay = product.getActualPrice();
            if (this.money >= priceToPay) {
                this.money -= priceToPay;
                this.products.add(product);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            if (products.isEmpty()) {
                return name + " - Ничего не куплено";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(name).append(" - ");
                for (int i = 0; i < products.size(); i++) {
                    sb.append(products.get(i).getName());
                    if (i != products.size() - 1) sb.append(", ");
                }
                return sb.toString();
            }
        }
    }

    // Обычный продукт
    static class Product {
        private String name;
        private double cost;

        public Product(String name, double cost) {
            setName(name);
            setCost(cost);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Название продукта не может быть пустым");
            }
            if (name.matches("\\d+")) {
                throw new IllegalArgumentException("Название продукта не может содержать только цифры");
            }
            if (name.trim().length() < 3) {
                throw new IllegalArgumentException("Название продукта должно быть не короче 3 символов");
            }
            this.name = name.trim();
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            if (cost <= 0) {
                throw new IllegalArgumentException("Стоимость продукта должна быть больше нуля");
            }
            this.cost = cost;
        }

        public double getActualPrice() {
            return cost;
        }
    }

    static class DiscountProduct extends Product {
        private double discount; // %
        private LocalDate discountEndDate;

        public DiscountProduct(String name, double cost, double discount, LocalDate discountEndDate) {
            super(name, cost);
            setDiscount(discount);
            setDiscountEndDate(discountEndDate);
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            if (discount < 0 || discount > 100) {
                throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
            }
            this.discount = discount;
        }

        public LocalDate getDiscountEndDate() {
            return discountEndDate;
        }

        public void setDiscountEndDate(LocalDate discountEndDate) {
            if (discountEndDate == null) {
                throw new IllegalArgumentException("Дата окончания скидки не может быть пустой");
            }
            this.discountEndDate = discountEndDate;
        }

        @Override
        public double getActualPrice() {
            if (LocalDate.now().isAfter(discountEndDate)) {
                return super.getCost();
            }
            return super.getCost() * (1 - discount / 100);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите покупателей в формате: имя=бюджет; имя=бюджет");
        List<Person> persons = new ArrayList<>();

        String personsLine = scanner.nextLine().trim();
        if (!personsLine.isEmpty()) {
            String[] personsArray = personsLine.split(";");
            for (String personStr : personsArray) {
                try {
                    String[] parts = personStr.trim().split("=");
                    String name = parts[0].trim();
                    double money = Double.parseDouble(parts[1].trim());
                    persons.add(new Person(name, money));
                } catch (Exception e) {
                    System.out.println("Ошибка ввода покупателя: " + personStr);
                }
            }
        }

        System.out.println("Введите продукты:");
        System.out.println("Для обычного: Название=Цена");
        System.out.println("Для скидочного: Название=Цена=Скидка%=ГГГГ-ММ-ДД");
        System.out.println("Напишите END для завершения");

        List<Product> productsCatalog = new ArrayList<>();

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("END")) break;
            if (line.isEmpty()) continue;

            String[] parts = line.split("=");

            try {
                if (parts.length == 2) {
                    // Обычный продукт
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    productsCatalog.add(new Product(name, price));
                    System.out.println("Продукт добавлен: " + name);
                } else if (parts.length == 4) {
                    // Скидочный продукт
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    double discount = Double.parseDouble(parts[2].trim());
                    LocalDate endDate = LocalDate.parse(parts[3].trim());
                    productsCatalog.add(new DiscountProduct(name, price, discount, endDate));
                    System.out.println("Скидочный продукт добавлен: " + name);
                } else {
                    System.out.println("Некорректный формат. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат числа.");
            } catch (IllegalArgumentException e) {
                // Сообщение из setName / setCost / setDiscount
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        System.out.println("Начинаем покупки (формат: Покупатель - Продукт)");
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("END")) break;

            String[] parts = line.split("-");
            if (parts.length < 2) {
                System.out.println("Некорректный ввод");
                continue;
            }

            String buyerName = parts[0].trim();
            String productName = parts[1].trim();

            Person buyer = persons.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(buyerName))
                    .findFirst().orElse(null);

            if (buyer == null) {
                System.out.println("Покупатель не найден");
                continue;
            }

            Product product = productsCatalog.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(productName))
                    .findFirst().orElse(null);

            if (product == null) {
                System.out.println("Продукт не найден");
                continue;
            }

            if (buyer.buyProduct(product)) {
                System.out.println(buyer.getName() + " купил " + product.getName());
            } else {
                System.out.println(buyer.getName() + " не может позволить себе " + product.getName());
            }
        }

        persons.forEach(System.out::println);
        scanner.close();
    }
}