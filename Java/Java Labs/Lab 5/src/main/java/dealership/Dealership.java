package dealership;

import car.Car;
import car.NewCar;
import car.UsedCar;

public class Dealership {
    public static void main(String[] args) {
        Car[] carInventory = new Car[6];

        carInventory[0] = new NewCar("N2312", 2006, 18000, 2000, 3000, 2000);
        carInventory[2] = new NewCar("N6467", 2006, 43000, 4000, 6000, 3000);
        carInventory[4] = new NewCar("N0864", 2007, 24000, 1200, 2500, 0);

        carInventory[1] = new UsedCar("U3425", 2003, 16000, 400, 40000, 0.15);
        carInventory[3] = new UsedCar("U2347", 1998, 8000, 700, 12000, 0.1);
        carInventory[5] = new UsedCar("U4739", 2005, 18000, 2400, 12000, 0.2);

        for (Car car : carInventory) {
            car.updateAssets();
        }

        System.out.println("Total Assets of Dealer = " + Car.getTotalAssets());
        System.out.println();
        System.out.println("Total Assets of NewCar = " + NewCar.getTotalAssets());
        System.out.println("Average New Car Price = " + NewCar.getTotalAssets() / NewCar.getTotalCars());
        System.out.println();
        System.out.println("Total Assets of UsedCar = " + UsedCar.getTotalAssets());
        System.out.println("Average New Car Price = " + UsedCar.getTotalAssets() / UsedCar.getTotalCars());

        for (Car car : carInventory) {
            System.out.println(car);
        }
    }
}
