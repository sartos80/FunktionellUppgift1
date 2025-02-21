package mongodbDemo;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MongoDBAtlasDownloadExample {
    MovieRepository m = new MovieRepository();
    private static MongoClient mongoClient;

    // Metod för att läsa connectionString från settings.properties och skapa en MongoClient
    private static MongoClient getConnection() {
        if (mongoClient == null) {
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

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                    .build();

            mongoClient = MongoClients.create(settings);
        }
        return mongoClient;
    }

    public MongoDBAtlasDownloadExample() {
        try {
            MongoClient mongoClient = getConnection();
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            // Hämta alla filmer från 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                movieList.add(Movie.fromDocument(doc)); // Movie.fromDocument() måste finnas
            }
            // Skriver ut alla filmer
            for (Movie movie : movieList) {
                System.out.println(movie);
            }
            //1- Hitta antal filmer från 1975
            System.out.println("antal filmer från 1975 " + m.countMoviesFromYear(movieList, 1975));
            //2- hitta längden på den film som var längst
            System.out.println(" längden på den film som var längst " +  m.findLongestRuntime(movieList));
            // 3- hur många unika genrer hade filmerna från 1975
            System.out.println("antal unika genrer " + m.countUniqueGenres(movieList));
            // 4- Vilka skådisar som spelade i den film som hade högst imdb-rating.
            System.out.println(" skådisar som spelade i den film som hade högst imdb-rating " + m.getCastOfHighestRatedMovie(movieList));
            //5-Vad är titeln på den film som hade minst antal skådisar listade?
            System.out.println("film som hade minst antal skådisar " + m.getTitleWithFewestActors(movieList));
            // 6-Hur många skådisar var med i mer än 1 film? Returnera ett tal.
            System.out.println("antal skådisar var med i mer än 1 film " + m.countActorsInMultipleMovies(movieList));
            // 7- Vad hette den skådis som var med i flest filmer? Returnera en String
            System.out.println(m.getActorWithMostMovies(movieList));
            //8- Hur många UNIKA språk har filmerna? Returnera ett tal
            System.out.println("antal unika språk " + m.countUniqueLanguages(movieList));
            //-9 Finns det någon titel som mer än en film har
            System.out.println(m.hasDuplicateTitles(movieList));
            // 10 Högre Ordningens Funktion
            System.out.println("antal unika språk " + m.count2UniqueLanguages(movieList, movie -> movie.getLanguages()));

        } catch(Exception e){
                e.printStackTrace();
            }
        }

public static void main(String[] args) {
    MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
}
}
