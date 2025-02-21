package mongodbDemo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieRepository {
    // 1- metod för att hitta antal filmer från 1975
    public long  countMoviesFromYear(List<Movie> movies, int year){
        return movies.stream()
                .filter(movie -> movie.getYear() == year)
                .count();
    }
    // 2- hitta längden på den film som var längst
    public int findLongestRuntime(List<Movie> movies){
        return movies.stream().mapToInt(Movie::getRuntime).max().orElse(0);

    }
    // 3- hur många unika genrer hade filmerna från 1975
    public long countUniqueGenres(List<Movie> movies){
        return movies.stream().filter(movie->movie.getYear()== 1975).flatMap(movie->movie.getGenres().stream()).distinct().count();
    }

    // 4 • Vilka skådisar som spelade i den film som hade högst imdb-rating.
    public  List<String> getCastOfHighestRatedMovie(List<Movie> movies) {
        return movies.stream()
                .filter(movie -> movie.getImdbRating() > 0) // Exkludera filmer utan betyg
                .max(Comparator.comparingDouble(Movie::getImdbRating)) // Hitta filmen med högst betyg
                .map(Movie::getCast) // Hämta skådespelarlistan för denna film
                .orElse(List.of()); // Returnera tom lista om inga filmer finns
    }

    // 5 -Vad är titeln på den film som hade minst antal skådisar listade?
    public  String getTitleWithFewestActors(List<Movie> movies) {
        return movies.stream()
                .filter(movie -> movie.getCast() != null && !movie.getCast().isEmpty()) // Exkludera filmer utan cast
                .min(Comparator.comparingInt(movie -> movie.getCast().size())) // Hitta filmen med minst antal skådespelare
                .map(Movie::getTitle) // Hämta titeln på denna film
                .orElse("No movies found");
    }
    // 6 -Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public long countActorsInMultipleMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                .entrySet().stream() // Skapa en ström av grupperade poster
                .filter(entry -> entry.getValue() > 1) // Filtrera ut skådespelare med fler än 1 förekomst
                .count(); //
    }
    // 7- Vad hette den skådis som var med i flest filmer? Returnera en String
    public  String getActorWithMostMovies(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getCast().stream()) // Slå samman alla cast-listor till en ström
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting())) // Gruppera efter skådespelare och räkna förekomster
                .entrySet().stream() // Skapa en ström av grupperade poster
                .max(Map.Entry.comparingByValue()) // Hitta posten med högst värde (antal filmer)
                .map(Map.Entry::getKey) // Hämta skådespelarens namn
                .orElse("No actors found"); // Returnera standardvärde om inga skådespelare finns
    }
    //• 8- Hur många UNIKA språk har filmerna? Returnera ett tal
    public  long countUniqueLanguages(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getLanguages().stream()) // Slå samman alla språklistor till en ström
                .distinct()
                .count();
    }
    // 9-Finns det någon titel som mer än en film har? Returnera en bool.
    public boolean hasDuplicateTitles(List<Movie> movies) {
        return movies.stream()
                .map(Movie::getTitle) // Hämta alla filmernas titlar
                .collect(Collectors.groupingBy(title -> title, Collectors.counting())) // Gruppera titlar och räkna antal filmer per titel
                .values() // Hämta värdena (antal filmer per titel)
                .stream() // Skapa en ny ström av dessa värden
                .anyMatch(count -> count > 1); // Kontrollera om någon titel har mer än en film
    }
    // 10-Högre Ordningens Funktion
    public long count2UniqueLanguages(List<Movie> movies, LanguageExtractor c) {
        return movies.stream()
                .flatMap(movie -> c.extractLanguages(movie).stream()) // Använd den funktionella metoden
                .distinct() // Tar bort dubletterade språk
                .count(); // Räknar antalet unika språk
    }


}
