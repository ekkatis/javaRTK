public class Car {

    private String number;    // Номер автомобиля
    private String model;     // Модель
    private String color;     // Цвет
    private int mileage;      // Пробег
    private double cost;     // Стоимость

    public Car(String number, String model, String color, int mileage, double cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if (mileage >= 0) {
            this.mileage = mileage;
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        }
    }

    @Override
    public String toString() {
        return String.format("Car[number=%s, model=%s, color=%s, mileage=%d, cost=%.2f]",
                number, model, color, mileage, cost);
    }
}