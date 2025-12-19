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

    public static Employee getEmployeeById(ObjectId id) {
        Document doc = getCollection().find(eq("_id", id)).first();
        return (doc == null) ? null : documentToEmployee(doc);
    }

    public static void save(SalaryRecord s) {
        Document doc = new Document()
                .append("employeeId", s.employeeId)
                .append("salary_type", s.salaryType)
                .append("base_salary", s.baseSalary)
                .append("medical", s.medical)
                .append("dependents", s.dependents);

        getCollection().insertOne(doc);
    }

    public static void update(Employee s) {
        if (s.mongoId == null) return;

        UpdateResult result = getCollection().updateOne(
                eq("_id", s.mongoId),
                new Document("$set", new Document()
                        .append("salary_type", s.salaryType)
                        .append("medical", s.medical)
                        .append("base_salary", s.baseSalary)
                        .append("dependents", s.dependents)
                        .append("employeeId", s.employeeId)
                )
        );
            
    }
}

