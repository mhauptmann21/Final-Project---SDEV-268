package server;

import java.util.List;

public class PayrollCalculator {

    public static double calculateGrossPay(String payType, double baseSalary, List<Double> hours) {
        double gross = 0;

        if (payType.equalsIgnoreCase("SALARY")) {
            gross = baseSalary / 52;
        } else {
            for (int i = 0; i < hours.size(); i++) {
                double daily = hours.get(i);
                boolean weekend = (i == 5 || i == 6);

                if (weekend) {
                    gross += daily + 1.5;
                } else if (daily > 8) {
                    gross += 8 + (daily - 8) * 1.5;
                } else {
                    gross += daily;
                }
            }
        }

        return gross;
    }

    public static double calculateMedical(String type) {
        return type.equalsIgnoreCase("FAMILY") ? 100 : 50;
    }

    public static double calculateDependentStipend(int dependents) {
        return dependents * 45;
    }

    public static double calculateStateTax(double taxable) {
        return taxable * 0.0315;
    }
    
    public static double calculateFederalTax(double taxable) {
        return taxable * 0.0765;
    }

    public static double calculateSocialSecurity(double taxable) {
        return taxable * 0.062;
    }

    public static double calculateMedicare(double taxable) {
        return taxable * 0.0145;
    }
}
