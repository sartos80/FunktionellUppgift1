package mongodbDemo;

import java.util.List;

public class TestData {

    public static List<Movie> createMovieList() {
        return List.of(
                new Movie("1", "Inception", 2010, List.of("Action", "Sci-Fi"), "Christopher Nolan", List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 8.8, List.of("English"), 148),
                new Movie("2", "Interstellar", 2014, List.of("Adventure", "Drama"), "Christopher Nolan", List.of("Matthew McConaughey", "Anne Hathaway"), 8.6, List.of("English"), 169),
                new Movie("3", "The Dark Knight", 2008, List.of("Action", "Crime"), "Christopher Nolan", List.of("Christian Bale", "Heath Ledger"), 9.0, List.of("English"), 152),
                new Movie("4", "Avatar", 2009, List.of("Action", "Fantasy"), "James Cameron", List.of("Sam Worthington", "Zoe Saldana"), 7.8, List.of("English"), 162),
                new Movie("5", "Titanic", 1997, List.of("Romance", "Drama"), "James Cameron", List.of("Leonardo DiCaprio", "Kate Winslet"), 7.9, List.of("English"), 194),
                new Movie("6", "Sample Movie", 1975, List.of("Comedy"), "Director X", List.of("Actor A", "Actor B"), 7.5, List.of("French"), 120),
                new Movie("7", "Another 1975 Movie", 1975, List.of("Drama"),
                        "Director Y", List.of("Actor C"), 8.0, List.of("German"), 150)
        );
    }
}

