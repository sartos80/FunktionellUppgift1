package mongodbDemo;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MongoClientConnectionExample {
    public static void main(String[] args) {

        getConnection();
    }
    private static MongoClient mongoClient;

    // Metod för att hämta MongoDB-anslutningen från en properties-fil
    private static MongoClient getConnection() {
        if (mongoClient == null) { // Se till att vi bara skapar anslutningen en gång
            Properties properties = new Properties();
            try (FileInputStream inputStream = new FileInputStream("src/mongodbDemo/setting.properties")) {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Error reading settings file", e);
            }

            String connectionString = properties.getProperty("connectionString");
            if (connectionString == null || connectionString.isEmpty()) {
                throw new RuntimeException("Connection string is missing in properties file");
            }

            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();

            // Skapa en ny klient och anslut till servern
            try (MongoClient mongoClient = MongoClients.create(settings)) {
                try {
                    MongoDatabase database = mongoClient.getDatabase("admin");
                    database.runCommand(new Document("ping", 1));
                    System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                } catch (MongoException e) {
                    e.printStackTrace();
                }
            }
        }
        return mongoClient;
    }}