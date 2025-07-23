import java.util.Random;
import java.util.Scanner;

public class Homeworkfour {

    public static class Television {
        private String brand;
        private double size;
        private boolean isSale;
        private int price;

        public Television(String brand, double size, boolean isSale, int price) {
            this.brand = brand;
            this.size = size;
            this.isSale = isSale;
            this.price = price;
        }

        public String getInfo() {
            return "Бренд: " + brand + ", размер: " + size + ", цена: " + price + " руб., в продаже: " + (isSale ? "да" : "нет");
        }

        public void saleOn() { isSale = true; }
        public void saleOff() { isSale = false; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        Television tv1 = new Television("Samsung", 50.0, true, 11000);
        System.out.println("Первый телевизор: " + tv1.getInfo());

        System.out.println("Введите бренд телевизора: ");
        String brand = scanner.nextLine();

        System.out.println("Введите диагональ телевизора в дюймах: ");
        double size = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера

        System.out.println("Введите статус продажи (Да/Нет): ");
        String saleOnInput = scanner.nextLine();
        boolean isSale = saleOnInput.equalsIgnoreCase("Да");

        System.out.println("Введите цену телевизора: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        Television tv2 = new Television(brand, size, false, price);
        if (isSale) tv2.saleOn();
        System.out.println("Второй телевизор: " + tv2.getInfo());

        String[] brands = {"Sony", "LG", "Panasonic", "Beko"};
        String randBrand = brands[rand.nextInt(brands.length)];
        double randSize = 20 + rand.nextDouble() * 50;
        int randPrice = 1000 + rand.nextInt(10000000);
        boolean randSale = rand.nextBoolean();

        Television tv3 = new Television(randBrand, randSize, false, randPrice);
        if (randSale) tv3.saleOn();

        System.out.println("Третий телевизор: " + tv3.getInfo());

        scanner.close();
    }
}