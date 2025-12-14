package server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Database {

    private static final String CONNECTION_STRING =
        "mongodb+srv://payroll_admin:admin_password@payrolldb.qayzedi.mongodb.net/?appName=PayrollDB";

    private static final String DATABASE_NAME = "payroll_system";

    private static MongoClient client;
    private static MongoDatabase database;

    static {
        client = MongoClients.create(CONNECTION_STRING);
        database = client.getDatabase(DATABASE_NAME);
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
