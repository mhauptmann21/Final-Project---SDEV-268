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
                .append("pay_type", e.payType)
                .append("base_salary", e.baseSalary)
                .append("medical", e.medical)
                .append("dependents", e.dependents)
                .append("date_of_birth", e.dateOfBirth)
                .append("date_hired", e.dateHired)
                .append("email", e.email)
                .append("username", e.username)
                .append("department", e.department)
                .append("job_title", e.jobTitle)
                .append("password", SecurityModule.md5Hash(e.password));

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
                    .append("pay_type", "SALARY")
                    .append("base_salary", 0.0)
                    .append("medical", "SINGLE")
                    .append("dependents", 0)
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

        UpdateResult result = getCollection().updateOne(
                eq("_id", e.mongoId),
                new Document("$set", new Document()
                        .append("first_name", e.firstName)
                        .append("last_name", e.lastName)
                        .append("status", e.status)
                        .append("pay_type", e.payType)
                        .append("base_salary", e.baseSalary)
                        .append("medical", e.medical)
                        .append("dependents", e.dependents)
                        .append("date_of_birth", e.dateOfBirth)
                        .append("date_hired", e.dateHired)
                        .append("email", e.email)
                        .append("department", e.department)
                        .append("job_title", e.jobTitle)
                )
        );
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
        e.payType = doc.getString("pay_type");

        Number salaryNum = doc.get("base_salary", Number.class);
        e.baseSalary = (salaryNum != null) ? salaryNum.doubleValue() : 0.0;

        e.medical = doc.getString("medical");
        e.dependents = doc.getInteger("dependents", 0);
        e.dateOfBirth = doc.getString("date_of_birth");
        e.dateHired = doc.getString("date_hired");
        e.email = doc.getString("email");
        e.username = doc.getString("username");
        e.department = doc.getString("department");
        e.jobTitle = doc.getString("job_title");

        return e;
    }
}