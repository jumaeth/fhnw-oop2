package streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static streams.StreamTasks2.findLargestRestaurant;

public class FindLargestRestaurantTest {

    Restaurant schlossMorgental = new Restaurant("Schloss Morgental", 4.4, 80,
            List.of("gehoben", "traditionell"));
    Restaurant hofgut = new Restaurant("Hofgut", 4.6, 55,
            List.of("gutbürgerlich", "regional"));
    Restaurant bibis = new Restaurant("Bibis", 4.8, 45,
            List.of("vegetarisch", "gesund", "regional"));
    Restaurant hirsch = new Restaurant("Zum Hirsch", 4.6, 40,
            List.of("modern", "gutbürgerlich"));
    Restaurant sol = new Restaurant("Sol", 4.5, 20,
            List.of("vegan", "modern"));

    @Test
    void matches() {
        var restaurants = List.of(bibis, hirsch, hofgut, schlossMorgental, sol);
        assertEquals("Bibis", findLargestRestaurant(restaurants, "vegetarisch"));
        assertEquals("Hofgut", findLargestRestaurant(restaurants, "gutbürgerlich"));
        assertEquals("Schloss Morgental", findLargestRestaurant(restaurants, "traditionell"));
        assertEquals("Schloss Morgental", findLargestRestaurant(restaurants, "gehoben"));

        restaurants = List.of(hirsch, hofgut, schlossMorgental, sol);
        assertEquals("Zum Hirsch", findLargestRestaurant(restaurants, "modern"));
        assertEquals("Sol", findLargestRestaurant(restaurants, "vegan"));
    }

    @Test
    void noMatch() {
        var restaurants = List.of(bibis, hirsch, hofgut, schlossMorgental, sol);
        assertEquals("No result", findLargestRestaurant(restaurants, "billig"));
        assertEquals("No result", findLargestRestaurant(restaurants, "chillig"));
        assertEquals("No result", findLargestRestaurant(restaurants, "grillig"));
    }

    @Test
    void empty() {
        assertEquals("No result", findLargestRestaurant(emptyList(), "modern"));
        assertEquals("No result", findLargestRestaurant(emptyList(), "vegan"));
        assertEquals("No result", findLargestRestaurant(emptyList(), "traditionell"));
    }
}
