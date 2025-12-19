package server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import javax.print.Doc;
import view.Salary;

public class SalaryDAO {

    private static MongoCollection<Document> getCollection() {
        MongoDatabase db = Database.getDatabase();
        return db.getCollection("employees");
    }

    public static SalaryRecord getByEmployeeId(Object employeeId) {
        Document doc = getCollection()
                .find(eq("employeeId", employeeId))
                .first();

        if (doc == null) return null;

        SalaryRecord s = new SalaryRecord();
        s.employeeId = doc.getObjectId("employeeId");
        s.salaryType = doc.getString("salary_type");
        s.baseSalary = ((Number) doc.get("base_salary")).doubleValue();
        s.medical = doc.getString("medical");
        s.dependents = doc.getInteger("dependents", 0);

        return s;
    }

    public static void save(SalaryRecord s) {
        Document doc = new Document()
                .append("employeeId", s.employeeId)
                .append("salaryType", s.salaryType)
                .append("baseSalary", s.baseSalary)
                .append("medical", s.medical)
                .append("dependents", s.dependents);

        getCollection().insertOne(doc);
    }

    public static void update(Employee s) {
        if (s.mongoId == null) return;

        UpdateResult result = getCollection().updateOne(
                eq("_id", s.mongoId),
                new Document("$set", new Document()
                        .append("salaryType", s.salaryType)
                        .append("medical", s.medical)
                        .append("baseSalary", s.baseSalary)
                        .append("dependents", s.dependents)
                        .append("employeeId", s.employeeId)
                )
        );
            
    }
}

