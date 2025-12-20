package server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class TimeEntryDAO {

    private static MongoCollection<Document> timeEntries() {
        MongoDatabase db = Database.getDatabase();
        return db.getCollection("time_entries");
    }

    private static MongoCollection<Document> locks() {
        MongoDatabase db = Database.getDatabase();
        return db.getCollection("time_entry_locks");
    }

    // Convert yyyy-mm-dd → week start (you can enhance later)
    public static LocalDate getWeekStart(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            throw new IllegalArgumentException("dateStr cannot be null");
        }
        return LocalDate.parse(dateStr);
    }

    // INSERT or UPDATE weekly totals
    public static void insert(TimeEntry t) {
        LocalDate weekStart = getWeekStart(t.date);

        Document existing = timeEntries().find(
                and(
                        eq("employeeId", t.employeeId),
                        eq("weekStart", weekStart.toString())
                )
        ).first();

        if (existing != null) {
            double newHours = existing.getDouble("hoursWorked") + t.hoursWorked;
            double newPTO = existing.getDouble("ptoHours") + t.ptoHours;

            timeEntries().updateOne(
                    eq("_id", existing.getObjectId("_id")),
                    Updates.combine(
                            Updates.set("hoursWorked", newHours),
                            Updates.set("ptoHours", newPTO)
                    )
            );
        } else {
            Document doc = new Document()
                    .append("employeeId", t.employeeId)
                    .append("weekStart", weekStart.toString())
                    .append("hoursWorked", t.hoursWorked)
                    .append("ptoHours", t.ptoHours)
                    .append("isLocked", t.isLocked);

            timeEntries().insertOne(doc);
        }
    }

    // UPDATE entry
    public static void update(TimeEntry t) {
        LocalDate weekStart = getWeekStart(t.date);

        timeEntries().updateOne(
                and(
                        eq("employeeId", t.employeeId),
                        eq("weekStart", weekStart.toString())
                ),
                Updates.combine(
                        Updates.set("hoursWorked", t.hoursWorked),
                        Updates.set("ptoHours", t.ptoHours),
                        Updates.set("isLocked", t.isLocked)
                )
        );
    }

    // DELETE entry
    public static void delete(String mongoId) {
        timeEntries().deleteOne(eq("_id", mongoId));
    }

    // GET entries for a week range
    public static List<TimeEntry> getWeek(String employeeId, String startDate, String endDate) {
        LocalDate start = getWeekStart(startDate);
        LocalDate end = getWeekStart(endDate);

        List<TimeEntry> entries = new ArrayList<>();

        for (Document doc : timeEntries().find(
                and(
                        eq("employeeId", employeeId),
                        gte("weekStart", start.toString()),
                        lte("weekStart", end.toString())
                )
        )) {
            TimeEntry t = new TimeEntry();
            t.mongoId = doc.getObjectId("_id");
            t.employeeId = doc.getInteger("employeeId");
            t.date = doc.getString("weekStart");
            t.hoursWorked = doc.getDouble("hoursWorked");
            t.ptoHours = doc.getDouble("ptoHours");
            t.isLocked = doc.getBoolean("isLocked");
            entries.add(t);
        }

        return entries;
    }

    // LOCK week
    public static void lockWeek(String employeeId, String weekStart, String weekEnd) {
        locks().updateOne(
                and(
                        eq("employeeId", employeeId),
                        eq("weekStart", weekStart)
                ),
                Updates.set("locked", true),
                new com.mongodb.client.model.UpdateOptions().upsert(true)
        );
    }

    // UNLOCK week
    public static void unlockWeek(String employeeId, String weekStart) {
        locks().updateOne(
                and(
                        eq("employeeId", employeeId),
                        eq("weekStart", weekStart)
                ),
                Updates.set("locked", false)
        );
    }

    // Check if locked
    public static boolean areEntriesLocked(String employeeId, String weekStart) {
        Document doc = locks().find(
                and(
                        eq("employeeId", employeeId),
                        eq("weekStart", weekStart),
                        eq("locked", true)
                )
        ).first();

        return doc != null;
    }

    // Get weekly hours
    public static double getWeeklyHours(String employeeId) {
        double total = 0;

        for (Document doc : timeEntries().find(eq("employeeId", employeeId))) {
            total += doc.getDouble("hoursWorked");
        }

        return total;
    }

    public static List<TimeEntry> getTimeEntriesByEmployeeId(ObjectId employeeId) {
        List<TimeEntry> entries = new ArrayList<>();
        for (Document doc : getCollection().find(eq("employee_id", employeeId))) {
            TimeEntry t = new TimeEntry();
            t.mongoId = doc.getObjectId("_id");
            t.employeeId = doc.getObjectId("employee_id");
            t.weekStart = doc.getString("weekStart");
            t.hoursWorked = doc.getDouble("hoursWorked");
            t.ptoHours = doc.getInteger("ptoHours");
            t.isLocked = doc.getBoolean("isLocked");
            entries.add(t);
        }
        return entries;
    }
}
