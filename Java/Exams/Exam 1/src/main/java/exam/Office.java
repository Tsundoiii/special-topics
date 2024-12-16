package exam;

public class Office {

    //employees are sorted by floor and room, e.g. [0][3] would be ground floor room 4
    Employee[][] employees;

    public Office(int floors, int rooms) {
        employees = new Employee[floors][rooms];
    }

    //Assigns an Employee to a specified room and floor
    public void assignRoom(Employee newEmp, int floor, int room) {
        /*
         * TODO: Complete the method according
         * to the comment description above.
         */

        employees[floor][room] = newEmp;
    }

    //Calculate the total pay (use the Employee getpay() method) for all employees on each floor.
    //Return an array where each element is the total dollars owed to all employees on that floor.
    //Some office rooms may be empty and therefore that 2D array location set to null.
    public double[] getEachFloorPay() {
        /*
         * TODO: Complete the method according
         * to the comment description above.
         */

        /*
         * Example returned result (for a 3-floor office):
         * a double array containing [3669.15, 4514.62, 2559.09]
         */

        double[] payments = new double[employees.length];

        for (int floor = 0; floor < employees.length; floor++) {
            double floorPayment = 0;

            for (Employee employee : employees[floor]) {
                if (employee != null) {
                    floorPayment += employee.getPay();
                }
            }

            payments[floor] = floorPayment;
        }

        return payments;
    }

    /*
     * Swaps the Employee's at two different locations (floor and room)
     */
    public void swapRooms(int floor1, int room1, int floor2, int room2) {
        /*
         * TODO: Complete the method according
         * to the comment description above.
         */

        Employee employee1 = employees[floor1][room1];
        Employee employee2 = employees[floor2][room2];

        employees[floor1][room1] = employee2;
        employees[floor2][room2] = employee1;
    }

}
