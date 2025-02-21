package mongodbDemo;

import java.util.List;
@FunctionalInterface
public interface LanguageExtractor {
    List<String> extractLanguages(Movie movie);
}