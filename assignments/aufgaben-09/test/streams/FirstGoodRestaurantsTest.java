package streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static streams.StreamTasks2.firstGoodRestaurants;

public class FirstGoodRestaurantsTest {

    Restaurant royalPalace = new Restaurant("Royal Palace", 4.9, 100,
            List.of("gehoben", "teuer", "modern"));
    Restaurant bibis = new Restaurant("Bibis", 4.8, 45,
            List.of("vegetarisch", "gesund", "regional"));
    Restaurant mikesSteakhouse = new Restaurant("Mike's Steakhouse", 4.7, 50,
            List.of("modern", "teuer"));
    Restaurant hirsch = new Restaurant("Zum Hirsch", 4.6, 40,
            List.of("modern", "gutbürgerlich"));
    Restaurant sol = new Restaurant("Sol", 4.5, 20,
            List.of("vegan", "modern"));

    Restaurant schlossMorgental = new Restaurant("Schloss Morgental", 4.4, 80,
            List.of("gehoben", "traditionell"));
    Restaurant ochsen = new Restaurant("Zum Ochsen", 4.3, 70,
            List.of("gutbürgerlich", "traditionell", "teuer"));
    Restaurant kloster = new Restaurant("Kloster", 4.2, 60,
            List.of("gutbürgerlich", "teuer"));
    Restaurant badischerHof = new Restaurant("Badischer Hof", 4.0, 25,
            List.of("gutbürgerlich", "regional"));
    Restaurant schwanen = new Restaurant("Zum Schwanen", 3.6, 35,
            List.of("deftig", "regional"));

    @Test
    void moreThanN() {
        var restaurants = List.of(
                schlossMorgental, ochsen, bibis, sol, royalPalace, kloster,
                hirsch, mikesSteakhouse);
        var firstGood = firstGoodRestaurants(restaurants, 3);
        assertEquals(List.of(bibis, sol, royalPalace), firstGood);

        firstGood = firstGoodRestaurants(restaurants, 2);
        assertEquals(List.of(bibis, sol), firstGood);

        firstGood = firstGoodRestaurants(restaurants, 1);
        assertEquals(List.of(bibis), firstGood);
    }

    @Test
    void lessThanN() {
        var restaurants = List.of(
                schlossMorgental, ochsen, bibis, sol, royalPalace, kloster);
        var firstGood = firstGoodRestaurants(restaurants, 3);
        assertEquals(List.of(bibis, sol, royalPalace), firstGood);

        firstGood = firstGoodRestaurants(restaurants, 4);
        assertEquals(List.of(bibis, sol, royalPalace), firstGood);

        firstGood = firstGoodRestaurants(restaurants, 5);
        assertEquals(List.of(bibis, sol, royalPalace), firstGood);
    }

    @Test
    void noneGood() {
        var restaurants = List.of(
                schlossMorgental, ochsen, kloster, badischerHof, schwanen);
        var firstGood = firstGoodRestaurants(restaurants, 1);
        assertEquals(emptyList(), firstGood);

        firstGood = firstGoodRestaurants(restaurants, 2);
        assertEquals(emptyList(), firstGood);

        firstGood = firstGoodRestaurants(restaurants, 5);
        assertEquals(emptyList(), firstGood);

        firstGood = firstGoodRestaurants(restaurants, 10);
        assertEquals(emptyList(), firstGood);
    }

    @Test
    void empty() {
        var firstGood = firstGoodRestaurants(emptyList(), 3);
        assertEquals(emptyList(), firstGood);

        firstGood = firstGoodRestaurants(emptyList(), 0);
        assertEquals(emptyList(), firstGood);
    }
}
