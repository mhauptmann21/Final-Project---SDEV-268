package server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import security.SecurityModule;

import static com.mongodb.client.model.Filters.eq;

public class LoginDAO {

    private MongoCollection<Document> getCollection() {
        MongoDatabase db = Database.getDatabase();
        return db.getCollection("employees");
    }

    public Employee login(String username, String password) {

        try {
            // Find employee by username
            Document doc = getCollection()
                    .find(eq("username", username))
                    .first();

            if (doc == null) {
                // Username not found
                return null;
            }

            String storedHash = doc.getString("password");
            String providedHash = SecurityModule.md5Hash(password);

            // Compare hashed password
            if (!providedHash.equals(storedHash)) {
                return null;
            }

            // Build Employee object
            Employee emp = new Employee();
            emp.mongoId = doc.getObjectId("_id");
            emp.firstName = doc.getString("first_name");
            emp.lastName = doc.getString("last_name");
            emp.email = doc.getString("email");
            emp.username = doc.getString("username");
            emp.status = doc.getString("status");
            emp.department = doc.getString("department");
            emp.jobTitle = doc.getString("job_title");
            emp.payType = doc.getString("pay_type");

            return emp;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
