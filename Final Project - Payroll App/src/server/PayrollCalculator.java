package server;

import java.util.List;

public class PayrollCalculator {

    /* ---------- GROSS PAY ---------- */

    public static double calculateGrossPay(
            String payType,
            double baseSalary,
            double hourlyRate,
            List<Double> hours
    ) {
        double gross = 0;

        // Null-safe check for SALARY
        if ("SALARY".equalsIgnoreCase(payType)) {
            return baseSalary / 52;
        }

        // Default to hourly if payType is null or not SALARY
        if (hours != null) {
            for (int i = 0; i < hours.size(); i++) {
                double dailyHours = hours.get(i);
                boolean weekend = (i == 5 || i == 6);

                if (weekend) {
                    gross += dailyHours * hourlyRate * 1.5;
                } else if (dailyHours > 8) {
                    double regular = 8 * hourlyRate;
                    double overtime = (dailyHours - 8) * hourlyRate * 1.5;
                    gross += regular + overtime;
                } else {
                    gross += dailyHours * hourlyRate;
                }
            }
        }

        return gross;
    }


    /* ---------- DEDUCTIONS ---------- */

    public static double calculateMedical(String type) {
        return "FAMILY".equalsIgnoreCase(type) ? 100 : 50;
    }

    public static double calculateDependentStipend(int dependents) {
        return dependents * 45;
    }

    public static double calculateStateTax(double taxable) {
        return taxable * 0.0315;
    }

    public static double calculateFederalTax(double taxable) {
        return taxable * 0.12;
    }

    public static double calculateSocialSecurity(double taxable) {
        return taxable * 0.062;
    }

    public static double calculateMedicare(double taxable) {
        return taxable * 0.0145;
    }

    /* ---------- NET PAY ---------- */

    public static double calculateNetPay(
            double gross,
            String medicalType,
            int dependents
    ) {
        double federal = calculateFederalTax(gross);
        double state = calculateStateTax(gross);
        double ss = calculateSocialSecurity(gross);
        double medicare = calculateMedicare(gross);
        double medical = calculateMedical(medicalType);
        double dependent = calculateDependentStipend(dependents);

        return gross
                - federal
                - state
                - ss
                - medicare
                - medical
                + dependent;
    }
}
