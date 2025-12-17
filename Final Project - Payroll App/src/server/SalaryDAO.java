package server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class SalaryDAO {

    private static MongoCollection<Document> getCollection() {
        MongoDatabase db = Database.getDatabase();
        return db.getCollection("salaries");
    }

    public static void upsertSalary(Employee e) {
        if (e.mongoId == null) return;

        Document existing = getCollection().find(eq("employeeId", e.mongoId)).first();

        Document doc = new Document()
                .append("employeeId", e.mongoId)
                .append("baseSalary", e.baseSalary)
                .append("payType", e.payType)
                .append("medical", e.medical)
                .append("dependents", e.dependents);

        if (existing == null) {
            getCollection().insertOne(doc);
        } else {
            getCollection().updateOne(eq("_id", existing.getObjectId("_id")),
                                      new Document("$set", doc));
        }
    }

    public static Document getSalary(Employee e) {
        if (e.mongoId == null) return null;
        return getCollection().find(eq("employeeId", e.mongoId)).first();
    }
}

