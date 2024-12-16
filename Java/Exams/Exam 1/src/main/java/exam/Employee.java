package exam;

import java.util.ArrayList;

public class Employee {
    private final double hourlyRate;
    private final ArrayList<Double> hoursLog;

    /**
     * Hourly pay rate is based on title:
     * "Project Lead": 52
     * "Senior Software Engineer": 41
     * "Software Engineer": 25
     */
    public Employee(String title) {
        /*
         * TODO: Initialize hoursLog and assign hourlyRate
         * based on the title argument. Hourly rates for each
         * title are commented above.
         */

        hoursLog = new ArrayList<>();

        hourlyRate = switch (title) {
            case "Project Lead" -> 52;
            case "Senior Software Engineer" -> 41;
            case "Software Engineer" -> 25;
            default -> throw new IllegalStateException("Unexpected value: " + title);
        };
    }

    public void logHours(double hours) {
        hoursLog.add(hours);
    }

    /**
     * Get paid for each logged hours worked
     * Remove logged hours that are paid during this method
     */
    public double getPay() {
        /*
         * TODO: Complete this method based on
         * the method description above
         */
        double pay = 0;

        for (double hours : hoursLog) {
            pay += hours * hourlyRate;
        }

        hoursLog.clear();

        return pay;
    }
}
