package car;

public class UsedCar extends Car {
    private static int usedCarCount;
    private static double usedCarTotalAssets;
    private final double mileage;
    private final double rateOfDepreciation;

    public UsedCar(String id, int year, double price, double commission, double mileage, double depRate) {
        super(id, year, price, commission);
        this.mileage = mileage;
        rateOfDepreciation = depRate;
        usedCarCount++;
    }

    public static double getTotalAssets() {
        return usedCarTotalAssets;
    }

    public static int getTotalCars() {
        return usedCarCount;
    }

    public String toString() {
        StringBuilder usedCarInfo = new StringBuilder(super.toString());
        usedCarInfo.insert(0, "\nUsed Car\n");
        usedCarInfo.append("Mileage = " + mileage + "\n");
        usedCarInfo.append("Total cost = " + computeTotal() + "\n");
        usedCarInfo.append("Deal = " + (goodBusiness() ? "Good" : "Bad"));
        return usedCarInfo.toString();
    }

    public double computeTotal() {
        return basePrice - mileage * rateOfDepreciation + commission;
    }

    @Override
    public void updateAssets() {
        totalAssets += computeTotal();
        usedCarTotalAssets += computeTotal();
    }

    @Override
    public double getMileage() {
        return mileage;
    }

    @Override
    public boolean goodBusiness() {
        return commission > computeTotal() * 0.04;
    }
}
