package car;

public class NewCar extends Car {
    private static int newCarCount;
    private static double newCarTotalAssets;
    private final double optionsCost;
    private final double rebate;

    public NewCar(String id, int year, double price, double commission, double options, double rebate) {
        super(id, year, price, commission);
        optionsCost = options;
        this.rebate = rebate;
        newCarCount++;
    }

    public static double getTotalAssets() {
        return newCarTotalAssets;
    }

    public static int getTotalCars() {
        return newCarCount;
    }

    public String toString() {
        StringBuilder newCarInfo = new StringBuilder(super.toString());
        newCarInfo.insert(0, "\nNew Car\n");
        newCarInfo.append("Option cost = " + optionsCost + "\n");
        newCarInfo.append("Rebate = " + rebate + "\n");
        newCarInfo.append("Total cost = " + computeTotal() + "\n");
        newCarInfo.append("Deal = " + (goodBusiness() ? "Good" : "Bad"));
        return newCarInfo.toString();
    }

    public double computeTotal() {
        return basePrice + optionsCost + commission - rebate;
    }

    @Override
    public void updateAssets() {
        totalAssets += computeTotal();
        newCarTotalAssets += computeTotal();
    }

    @Override
    public double getMileage() {
        return 0;
    }

    @Override
    public boolean goodBusiness() {
        return commission >= basePrice * 0.08;
    }
}
