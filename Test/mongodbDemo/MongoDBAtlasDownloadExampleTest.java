package mongodbDemo;
import mongodbDemo.MongoDBAtlasDownloadExample;
import mongodbDemo.Movie;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MongoDBAtlasDownloadExampleTest {
    MovieRepository m = new MovieRepository();
    List<Movie> movies = TestData.createMovieList(); // Använd TestData för att skapa filmlistan

    @Test
    public void testCountMoviesFromYear() {
        // Anropa funktionen
        long result = m.countMoviesFromYear(movies, 1975);

        // Kontrollera resultatet
        assertEquals(2, result); // Vi förväntar oss 2 filmer från 1975
    }

    @Test
    public void testfindLongestRuntime() {
        // Kontrollera resultatet
        assertEquals(194, m.findLongestRuntime(movies)); // Vi förväntar oss 194
    }

    @Test
    public void testCountUniqueGenres() {
        // Anropa funktionen
        assertEquals(2, m.countUniqueGenres(movies)); // Vi förväntar oss 2 unika genrer
    }

    @Test
    //Vilka skådisar som spelade i den film som hade högst imdb-rating.
    public void testGetCastOfHighestRatedMovie() {
        // Anropa funktionen
        List<String> result = m.getCastOfHighestRatedMovie(movies);

        // Kontrollera resultatet
        List<String> expected = List.of("Christian Bale", "Heath Ledger");
        assertEquals(expected, result);
    }

    @Test
    //Vad är titeln på den film som hade minst antal skådisar listade?
    public void testGetTitleWithFewestActors_AllMoviesHaveSameNumberOfActors() {
        // Anropa metoden
        String expected = "Another 1975 Movie";
        assertEquals(expected,m.getTitleWithFewestActors(movies) );
    }

    @Test
    //Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public void testCountActorsInMultipleMovies() {
        // Förväntat resultat
        long expected = 1; // "Leonardo DiCaprio" är med i två filmer (Inception och Titanic)
        assertEquals(expected, m.countActorsInMultipleMovies(movies));
    }

    @Test
    // Vad hette den skådis som var med i flest filmer?
    public void testGetActorWithMostMovies() {
        String expected = "Leonardo DiCaprio";
        // Kontrollera resultatet
        assertEquals(expected,m.getActorWithMostMovies(movies) );
    }

    @Test
    public void testCountUniqueLanguages() {
        long expected = 3;
        assertEquals(expected, m.countUniqueLanguages(movies));
    }


    @Test
    public void testHasDuplicateTitles_NoDuplicates() {
        boolean expected = false; // Alla titlar är unika
        // Kontrollera resultatet
        assertEquals(expected,  m.hasDuplicateTitles(movies));
    }

    @Test
    public void testCount2UniqueLanguages() {
        // Skapa en LanguageExtractor-instans (Lambda-uttryck)
        LanguageExtractor c = movie -> movie.getLanguages();
        long expected = 3; // Tre unika språk: "English", "French", "German"
        assertEquals(expected , m.count2UniqueLanguages(movies, c) );
    }}