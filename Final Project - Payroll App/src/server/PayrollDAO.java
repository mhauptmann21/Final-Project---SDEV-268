package server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class PayrollDAO {

    private static MongoCollection<Document> collection() {
        MongoDatabase db = Database.getDatabase();
        return db.getCollection("payroll");
    }

    public static void insertPayroll(
        int employeeId,
        String periodStart,
        String periodEnd,
        double grossPay,
        double medicalDeduction,
        double dependentsStipend,
        double stateTax,
        double federalEmp,
        double federalEmployer,
        double ssEmp,
        double ssEmployer,
        double medicareEmp,
        double medicareEmployer,
        double netPay
    ) {
        try {
            Document payroll = new Document()
                    .append("employeeId", employeeId)
                    .append("periodStart", periodStart)
                    .append("periodEnd", periodEnd)
                    .append("grossPay", grossPay)
                    .append("medicalDeduction", medicalDeduction)
                    .append("dependentsStipend", dependentsStipend)
                    .append("stateTax", stateTax)
                    .append("federalTaxEmp", federalEmp)
                    .append("federalTaxEmployer", federalEmployer)
                    .append("socialSecEmp", ssEmp)
                    .append("socialSecEmployer", ssEmployer)
                    .append("medicareEmp", medicareEmp)
                    .append("medicareEmployer", medicareEmployer)
                    .append("netPay", netPay)
                    .append("createdAt", System.currentTimeMillis());

            collection().insertOne(payroll);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get most recent payroll for employee
    public static Payroll getLatestPayrollForEmployee(int employeeId) {
        try {
            Document doc = collection()
                    .find(eq("employeeId", employeeId))
                    .sort(Sorts.descending("createdAt"))
                    .first();

            if (doc == null) {
                return null;
            }

            Payroll p = new Payroll();
            p.mongoId = doc.getObjectId("_id");
            p.employeeId = doc.getInteger("employeeId");
            p.periodStart = doc.getString("periodStart");
            p.periodEnd = doc.getString("periodEnd");
            p.grossPay = doc.getDouble("grossPay");
            p.medicalDeduction = doc.getDouble("medicalDeduction");
            p.dependentsStipend = doc.getDouble("dependentsStipend");
            p.stateTax = doc.getDouble("stateTax");
            p.federalTaxEmp = doc.getDouble("federalTaxEmp");
            p.federalTaxEmployer = doc.getDouble("federalTaxEmployer");
            p.socialSecEmp = doc.getDouble("socialSecEmp");
            p.socialSecEmployer = doc.getDouble("socialSecEmployer");
            p.medicareEmp = doc.getDouble("medicareEmp");
            p.medicareEmployer = doc.getDouble("medicareEmployer");
            p.netPay = doc.getDouble("netPay");

            return p;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
