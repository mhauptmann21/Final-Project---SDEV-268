package server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import security.SecurityModule;

public class EmployeeDAO {

    /* COLLECTION ACCESS */

    private static MongoCollection<Document> getCollection() {
        MongoDatabase db = Database.getDatabase();
        return db.getCollection("employees");
    }

    /* INSERT EMPLOYEE (FULL) */

    public static void insertEmployee(Employee e) {
        Document doc = new Document()
                .append("first_name", e.firstName)
                .append("last_name", e.lastName)
                .append("status", e.status)
                .append("date_of_birth", e.dateOfBirth)
                .append("date_hired", e.dateHired)
                .append("email", e.email)
                .append("username", e.username)
                .append("department", e.department)
                .append("job_title", e.jobTitle)
                .append("password", SecurityModule.md5Hash(e.password))
                .append("address1", e.address1)
                .append("address2", e.address2)
                .append("city", e.city)
                .append("state", e.state)
                .append("zip", e.zip);

        if (e.password != null && !e.password.isEmpty()) {
            doc.append("password", SecurityModule.md5Hash(e.password));
        }

        getCollection().insertOne(doc);
        e.mongoId = doc.getObjectId("_id");
    }

    /* REGISTER NEW EMPLOYEE */

    public static boolean insertNewEmployee(
            String firstName,
            String lastName,
            String email,
            String username,
            String password
    ) {
        try {
            Document existing = getCollection()
                    .find(eq("username", username))
                    .first();

            if (existing != null) return false;

            Document employee = new Document()
                    .append("first_name", firstName)
                    .append("last_name", lastName)
                    .append("email", email)
                    .append("username", username)
                    .append("password", SecurityModule.md5Hash(password))
                    .append("status", "ACTIVE")
                    .append("date_of_birth", "2000-01-01")
                    .append("date_hired", java.time.LocalDate.now().toString())
                    .append("department", "NONE")
                    .append("job_title", "New Hire");

            getCollection().insertOne(employee);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* UPDATE EMPLOYEE */

    public static void updateEmployee(Employee e) {
        if (e.mongoId == null) return;

        Document updateDoc = new Document();

        if (e.firstName != null && !e.firstName.isEmpty()) updateDoc.append("first_name", e.firstName);
        if (e.surName != null && !e.surName.isEmpty()) updateDoc.append("sur_name", e.surName);
        if (e.lastName != null && !e.lastName.isEmpty()) updateDoc.append("last_name", e.lastName);
        if (e.status != null && !e.status.isEmpty()) updateDoc.append("status", e.status);
        if (e.dateOfBirth != null && !e.dateOfBirth.isEmpty()) updateDoc.append("date_of_birth", e.dateOfBirth);
        if (e.gender != null && !e.gender.isEmpty()) updateDoc.append("gender", e.gender);
        if (e.dateHired != null && !e.dateHired.isEmpty()) updateDoc.append("date_hired", e.dateHired);
        if (e.email != null && !e.email.isEmpty()) updateDoc.append("email", e.email);
        if (e.department != null && !e.department.isEmpty()) updateDoc.append("department", e.department);
        if (e.jobTitle != null && !e.jobTitle.isEmpty()) updateDoc.append("job_title", e.jobTitle);
        if (e.username != null && !e.username.isEmpty()) updateDoc.append("username", e.username);
        if (e.password != null && !e.password.isEmpty()) updateDoc.append("password", SecurityModule.md5Hash(e.password));
        if (e.address1 != null && !e.address1.isEmpty()) updateDoc.append("address1", e.address1);
        if (e.address2 != null && !e.address2.isEmpty()) updateDoc.append("address2", e.address2);
        if (e.city != null && !e.city.isEmpty()) updateDoc.append("city", e.city);
        if (e.state != null && !e.state.isEmpty()) updateDoc.append("state", e.state);
        if (e.zip != null && !e.zip.isEmpty()) updateDoc.append("zip", e.zip);

        if (e.password != null && !e.password.isEmpty()) {
            updateDoc.append("password", SecurityModule.md5Hash(e.password));
        }

        if (updateDoc.isEmpty()) {
            System.out.println("No fields to update for employee " + e.mongoId);
            return;
        }

        try {
            UpdateResult result = getCollection().updateOne(
                    eq("_id", e.mongoId),
                    new Document("$set", updateDoc)
            );

            if (result.getModifiedCount() > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("No changes were made.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*  DELETE EMPLOYEE  */

    public static void deleteEmployee(ObjectId id) {
        DeleteResult result = getCollection().deleteOne(eq("_id", id));
    }

    /* GET EMPLOYEE */

    public static Employee getEmployeeById(ObjectId id) {
        Document doc = getCollection().find(eq("_id", id)).first();
        return (doc == null) ? null : documentToEmployee(doc);
    }

    public static Employee getEmployeeByName(String first, String last) {
        Document doc = getCollection().find(
                new Document("first_name", first)
                        .append("last_name", last)
        ).first();

        return (doc == null) ? null : documentToEmployee(doc);
    }

    

    private static Employee documentToEmployee(Document doc) {

        Employee e = new Employee();

        e.mongoId = doc.getObjectId("_id");
        e.firstName = doc.getString("first_name");
        e.lastName = doc.getString("last_name");
        e.status = doc.getString("status");
        e.dateOfBirth = doc.getString("date_of_birth");
        e.dateHired = doc.getString("date_hired");
        e.email = doc.getString("email");
        e.username = doc.getString("username");
        e.department = doc.getString("department");
        e.jobTitle = doc.getString("job_title");

        return e;
    }
}