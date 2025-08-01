import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Attestation01 {

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
                System.out.println("Имя не может быть пустым");
                throw new IllegalArgumentException();
            }
            if (name.trim().length() < 3) {
                System.out.println("Имя не может быть короче 3 символов");
                throw new IllegalArgumentException();
            }
            this.name = name.trim();
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            if (money < 0) {
                System.out.println("Деньги не могут быть отрицательным числом");
                throw new IllegalArgumentException();
            }
            this.money = money;
        }

        public List<Product> getProducts() {
            return products;
        }

        public boolean buyProduct(Product product) {
            if (this.money >= product.getCost()) {
                this.money -= product.getCost();
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
                System.out.println("Название продукта не может быть пустым");
                throw new IllegalArgumentException();
            }
            this.name = name.trim();
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            if (cost < 0) {
                System.out.println("Стоимость продукта не может быть отрицательной");
                throw new IllegalArgumentException();
            }
            this.cost = cost;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Ввод покупателей
        System.out.println("Введите покупателей в формате: имя=бюджет; имя=бюджет и тд");
        List<Person> persons = new ArrayList<>();

        String personsLine = scanner.nextLine().trim();

        if (!personsLine.isEmpty()) {
            String[] personsArray = personsLine.split(";");
            for (String personStr : personsArray) {
                personStr = personStr.trim();
                if (personStr.isEmpty()) continue;

                String[] parts = personStr.split("=");
                if (parts.length != 2) {
                    System.out.println("Некорректный формат для покупателя: " + personStr);
                    continue;
                }

                String name = parts[0].trim();
                String moneyStr = parts[1].trim();

                try {
                    double money = Double.parseDouble(moneyStr);
                    if (name.isEmpty()) {
                        System.out.println("Имя не может быть пустым");
                        continue;
                    }
                    if (name.length() < 3) {
                        System.out.println("Имя не может быть короче 3 символов");
                        continue;
                    }
                    if (money < 0) {
                        System.out.println("Деньги не могут быть отрицательным числом");
                        continue;
                    }
                    Person person = new Person(name, money);
                    persons.add(person);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректное число для денег: " + moneyStr);
                    continue;
                } catch (IllegalArgumentException e) {
                    // Исключение уже обработано внутри конструктора
                    continue;
                }
            }
        }

        System.out.println("Введите продукты в формате: Название=Цена; Название=Цена");

        List<Product> productsCatalog = new ArrayList<>();

        while(true){
            try{
                String line=scanner.nextLine().trim();
                if(line.equalsIgnoreCase("END")) break;

                if(line.isEmpty()) continue;

                String[] productEntries=line.split(";");

                for(String entry : productEntries){
                    entry=entry.trim();
                    if(entry.isEmpty()) continue;

                    String[] parts=entry.split("=");
                    if(parts.length!=2){
                        System.out.println("Некорректный формат продукта: " + entry);
                        continue;}

                    String productName=parts[0].trim();

                    double productCost;
                    try{
                        productCost=Double.parseDouble(parts[1].trim());
                    }catch(NumberFormatException e){
                        System.out.println("Некорректное число для стоимости продукта: " + parts[1]);
                        continue;
                    }

                    if(productName.isEmpty()){
                        System.out.println("Название продукта не может быть пустым");
                        continue;}

                    if(productCost<0){
                        System.out.println("Стоимость продукта не может быть отрицательной");
                        continue;}

                    Product product=new Product(productName,productCost);
                    productsCatalog.add(product);
                }

            }catch(Exception e){
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }

        System.out.println("Начинаем покупки:");

        while(true){
            System.out.println("Введите покупателя и продукт через дефис (-), например: Павел - Хлеб");

            String line=scanner.nextLine().trim();

            if(line.equalsIgnoreCase("END")) break;

            try{
                String[] parts=line.split("-");
                if(parts.length<2){
                    System.out.println("Некорректный формат ввода. Попробуйте снова.");
                    continue;}

                String buyerName=parts[0].trim();
                String productName=parts[1].trim();

                Person buyer=null;
                for(Person p:persons){
                    if(p.getName().equalsIgnoreCase(buyerName)){
                        buyer=p;break;}
                }

                if(buyer==null){
                    System.out.println("Покупатель с именем "+buyerName+" не найден.");
                    continue;}

                Product product=null;
                for(Product p:productsCatalog){
                    if(p.getName().equalsIgnoreCase(productName)){
                        product=p;break;}
                }

                if(product==null){
                    System.out.println("Продукт "+productName+" не найден.");
                    continue;}

                boolean success=buyer.buyProduct(product);

                if(success){
                    System.out.println(buyer.getName()+" купил "+product.getName());
                }else{
                    System.out.println(buyer.getName()+" не может позволить себе "+product.getName());
                }

            }catch(Exception e){
                System.out.println("Ошибка при обработке ввода. Попробуйте снова.");
            }
        }

        // Вывод результатов
        for(Person p:persons){
            System.out.println(p.toString());
        }

        scanner.close();

    }
}