package streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static streams.StreamTasks2.collectCategories;

public class CollectCategoriesTest {

    Restaurant royalPalace = new Restaurant("Royal Palace", 4.9, 100,
            List.of("gehoben", "teuer", "modern"));
    Restaurant bibis = new Restaurant("Bibis", 4.8, 45,
            List.of("vegetarisch", "gesund", "regional"));
    Restaurant mikesSteakhouse = new Restaurant("Mike's Steakhouse", 4.7, 50,
            List.of("modern", "teuer"));
    Restaurant hirsch = new Restaurant("Zum Hirsch", 4.6, 40,
            List.of("modern", "gutbürgerlich"));
    Restaurant hofgut = new Restaurant("Hofgut", 4.6, 55,
            List.of("gutbürgerlich", "regional"));
    Restaurant sol = new Restaurant("Sol", 4.5, 20,
            List.of("vegan", "modern"));

    @Test
    void single() {
        var categories = collectCategories(List.of(hofgut));
        assertEquals(List.of("gutbürgerlich", "regional"), categories);

        categories = collectCategories(List.of(bibis));
        assertEquals(List.of("vegetarisch", "gesund", "regional"), categories);
    }

    @Test
    void noDuplicates() {
        var restaurants = List.of(hofgut, sol);
        var categories = collectCategories(restaurants);
        var expected = List.of("gutbürgerlich", "regional", "vegan", "modern");

        restaurants = List.of(royalPalace, bibis);
        categories = collectCategories(restaurants);
        expected = List.of(
                "gehoben", "teuer", "modern", "vegetarisch", "gesund", "regional");
        assertEquals(expected, categories);
    }

    @Test
    void duplicates() {
        var restaurants = List.of(
                royalPalace, bibis, mikesSteakhouse, hirsch, hofgut, sol);
        var categories = collectCategories(restaurants);
        var expected = List.of(
                "gehoben", "teuer", "modern", "vegetarisch",
                "gesund", "regional", "gutbürgerlich", "vegan");
        assertEquals(expected, categories);

        restaurants = List.of(bibis, hirsch, sol);
        categories = collectCategories(restaurants);
        expected = List.of(
                "vegetarisch", "gesund", "regional", "modern", "gutbürgerlich", "vegan");
        assertEquals(expected, categories);
    }
}
