package car;

public abstract class Car {
    protected static double totalAssets;
    private final String id;
    private final int year;
    protected double basePrice;
    protected double commission;

    public Car(String id, int year, double basePrice, double commission) {
        this.id = id;
        this.year = year;
        this.basePrice = basePrice;
        this.commission = commission;
    }

    public static double getTotalAssets() {
        return totalAssets;
    }

    public String toString() {
        String carInfo = "Vehicle ID = " + id + "\n" +
                "Model year = " + year + "\n" +
                "Base price = " + basePrice + "\n" +
                "Commission = " + "\n";
        return carInfo;
    }

    abstract public void updateAssets();

    abstract public double getMileage();

    abstract public boolean goodBusiness();
}